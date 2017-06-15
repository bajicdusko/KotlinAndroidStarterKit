package com.bajicdusko.kotlinstarterkit.ui

import android.os.Build
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.Html
import android.text.InputType
import android.text.Spanned
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.bajicdusko.kotlinstarterkit.R
import com.bajicdusko.kotlinstarterkit.ui.fragment.BaseFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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

fun EditText.isBlank() = text.toString().isBlank()
fun EditText.validateNotEmpty(): Boolean {
    if (isBlank()) {
        error = context.getString(R.string.field_cannot_be_empty)
        return false
    } else {
        error = null
        return true
    }
}

fun TextInputLayout.validateNotEmpty(): Boolean {
    val editText = (getChildAt(0) as FrameLayout).getChildAt(0) as EditText
    var validateNotEmpty = editText.validateNotEmpty()
    if (editText.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
        if (isPasswordVisibilityToggleEnabled && !validateNotEmpty) {
            isPasswordVisibilityToggleEnabled = false
        } else if (!isPasswordVisibilityToggleEnabled && validateNotEmpty) {
            isPasswordVisibilityToggleEnabled = true
        }
    }
    return validateNotEmpty
}

fun BaseFragment.initDisposables() {
    if (disposables?.isDisposed ?: true) {
        disposables = CompositeDisposable()
    }
}

fun BaseFragment.rxTransaction(subscription: () -> Disposable) {
    initDisposables()
    disposables?.add(subscription())
}

fun BaseFragment.rxDispose() {
    if (!(disposables?.isDisposed ?: true)) {
        disposables?.dispose()
    }
}

fun String?.asHtml(): Spanned? {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        return Html.fromHtml(this)
    }
}
