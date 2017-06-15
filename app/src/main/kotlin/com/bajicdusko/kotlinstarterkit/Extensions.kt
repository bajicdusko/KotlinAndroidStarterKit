package com.bajicdusko.kotlinstarterkit

import com.crashlytics.android.Crashlytics
import timber.log.Timber

/**
 * Created by Dusko Bajic on 12.06.17.
 * GitHub @bajicdusko
 */


fun String?.isNotNullOrEmpty() = !isNullOrEmpty()

fun Timber.Tree.crashlyticsLogMessage(logMessage: String?) {
    if (!BuildConfig.DEBUG && Crashlytics.getInstance() != null && logMessage.isNotNullOrEmpty()) {
        Crashlytics.log(logMessage)
    }
}

fun Timber.Tree.crashlyticsException(t: Throwable?) {
    if (!BuildConfig.DEBUG && Crashlytics.getInstance() != null) {
        Crashlytics.logException(t)
    }
}

fun Timber.Tree.crashlyticsLog(logMessage: String?, t: Throwable?) {
    crashlyticsLogMessage(logMessage)
    crashlyticsException(t)
}