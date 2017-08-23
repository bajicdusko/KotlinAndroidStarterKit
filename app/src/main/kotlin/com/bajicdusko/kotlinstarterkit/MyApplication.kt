package com.bajicdusko.kotlinstarterkit

import android.app.Application
import android.util.Log
import com.bajicdusko.kotlinstarterkit.data.getErrorMessage
import com.bajicdusko.kotlinstarterkit.di.activity.ActivityModule
import com.bajicdusko.kotlinstarterkit.di.app.AndroidModule
import com.bajicdusko.kotlinstarterkit.di.app.ApplicationComponent
import com.bajicdusko.kotlinstarterkit.di.app.ApplicationModule
import com.bajicdusko.kotlinstarterkit.di.app.DaggerApplicationComponent
import com.bajicdusko.kotlinstarterkit.ui.BaseActivity
import timber.log.Timber

/**
 * Created by Dusko Bajic on 28.05.17.
 * GitHub @bajicdusko
 */
class MyApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .androidModule(AndroidModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        //todo Uncomment line below and add meta tag to AndroidManifest.xml (or create a fabric.properties file in root) in order to enable Crashlytics reporting.
        //Fabric.with(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(ExtendedDebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }

        appComponent.inject(this)
    }

    fun activityComponent(baseActivity: BaseActivity) =
            appComponent.activityBuilder().activityModule(ActivityModule(baseActivity)).build()

    class ExtendedDebugTree : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
            if (priority == Log.ERROR && t != null) {
              val exceptionMessage = t.getErrorMessage()
                if (exceptionMessage.isNotNullOrEmpty() && message.isNotNullOrEmpty()) {
                    super.log(priority, tag, "Exception message: $exceptionMessage - From code message: $message.", t)
                } else if (exceptionMessage.isNotNullOrEmpty()) {
                    super.log(priority, tag, "Exception message only: $exceptionMessage", t)
                } else {
                    super.log(priority, tag, message, t)
                }
            } else {
                super.log(priority, tag, message, t)
            }
        }
    }

    class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
            if (priority == Log.ERROR && t != null) {
              val exceptionMessage = t.getErrorMessage()
                var logMessage: String? = null;
                if (exceptionMessage.isNotNullOrEmpty() && message.isNotNullOrEmpty()) {
                    logMessage = "Exception message: $exceptionMessage - It happened: $message."
                } else if (exceptionMessage.isNotNullOrEmpty()) {
                    logMessage = "Exception message only: $exceptionMessage"
                }

                crashlyticsLog(logMessage, t)
            }
        }
    }
}