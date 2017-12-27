package com.bajicdusko.kotlinstarterkit.presenter.questions

import android.os.Bundle
import com.bajicdusko.kotlinstarterkit.domain.ANDROID_TAG
import com.bajicdusko.kotlinstarterkit.domain.interactor.GetQuestions
import com.bajicdusko.kotlinstarterkit.domain.model.SOQuestion
import com.bajicdusko.kotlinstarterkit.presenter.FragmentPresenter
import com.bajicdusko.kotlinstarterkit.presenter.rxTransaction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class QuestionsPresenter @Inject constructor() : FragmentPresenter {

  @Inject lateinit var getQuestions: GetQuestions

  override var disposables: CompositeDisposable = CompositeDisposable()

  var view: View? = null

  fun loadQuestions() {
    rxTransaction({
      getQuestions.execute(ANDROID_TAG)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe({ view?.showData(it) }, { view?.handleError(it, "On Questions Loading") })
    })
  }

  override fun onSaveInstanceState(outState: Bundle?) {}

  override fun onRestoreInstanceState(savedInstanceState: Bundle?) {}

  override fun restore() {}

  interface View : FragmentPresenter.View {
    fun showData(list: List<SOQuestion?>?)
    fun handleError(throwable: Throwable?, location: String?)
  }
}