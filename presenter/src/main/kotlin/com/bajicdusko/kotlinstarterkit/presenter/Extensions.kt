package com.bajicdusko.kotlinstarterkit.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
fun Presenter.initDisposables() {
  if (disposables.isDisposed) {
    disposables = CompositeDisposable()
  }
}

fun Presenter.rxDispose() {
  if (!disposables.isDisposed) {
    disposables.dispose()
  }
}

inline fun Presenter.rxTransaction(subscription: () -> Disposable) {
  initDisposables()
  disposables.add(subscription())
}