package com.bajicdusko.kotlinstarterkit

import android.app.Application
import com.bajicdusko.kotlinstarterkit.di.activity.ActivityModule
import com.bajicdusko.kotlinstarterkit.di.app.ApplicationComponent
import com.bajicdusko.kotlinstarterkit.di.app.ApplicationModule
import com.bajicdusko.kotlinstarterkit.di.app.DaggerApplicationComponent
import com.bajicdusko.kotlinstarterkit.ui.BaseActivity

/**
 * Created by Dusko Bajic on 28.05.17.
 * GitHub @bajicdusko
 */
class MyApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    fun activityComponent(baseActivity: BaseActivity) =
            appComponent.activityBuilder().activityModule(ActivityModule(baseActivity)).build()
}