package com.bajicdusko.kotlinstarterkit

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

/**
 * Created by Dusko Bajic on 12.06.17.
 * GitHub @bajicdusko
 */


fun String?.isNotNullOrEmpty() = !isNullOrEmpty()

fun Timber.Tree.crashlyticsLogMessage(logMessage: String?) {
    try {
        if (!BuildConfig.DEBUG && Crashlytics.getInstance() != null && logMessage.isNotNullOrEmpty()) {
            Crashlytics.log(logMessage)
        }
    } catch(e: Exception) {
        Log.e("Crashlytics", "Fabric not initialized.", e)
    }
}

fun Timber.Tree.crashlyticsException(t: Throwable?) {
    try {
        if (!BuildConfig.DEBUG && Crashlytics.getInstance() != null) {
            Crashlytics.logException(t)
        }
    } catch(e: Exception) {
        Log.e("Crashlytics", "Fabric not initialized.", e)
    }
}

fun Timber.Tree.crashlyticsLog(logMessage: String?, t: Throwable?) {
    crashlyticsLogMessage(logMessage)
    crashlyticsException(t)
}