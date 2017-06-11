package com.bajicdusko.kotlinstarterkit.ui

import android.support.v4.app.Fragment
import android.widget.Toast
import timber.log.Timber

/**
 * Created by Dusko Bajic on 05.06.17.
 * GitHub @bajicdusko
 */

fun Fragment.toast(message: String?) {
    try {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    } catch(e: Exception) {
        Timber.e(e, "On Fragment Toast")
    }
}