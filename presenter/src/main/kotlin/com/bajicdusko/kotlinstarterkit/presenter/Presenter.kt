package com.bajicdusko.kotlinstarterkit.presenter

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable

interface Presenter {

    var disposables: CompositeDisposable

    fun dispose() {
        rxDispose()
    }

    fun onSaveInstanceState(outState: Bundle?)

    fun onRestoreInstanceState(savedInstanceState: Bundle?)

    fun restore()

    interface View {
        fun dispose()

        fun restore()
    }

}