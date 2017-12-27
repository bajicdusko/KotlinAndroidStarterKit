package com.bajicdusko.kotlinstarterkit.presenter.questions

import com.bajicdusko.kotlinstarterkit.presenter.ViewHolderPresenter
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
class QuestionViewHolderPresenter @Inject constructor() : ViewHolderPresenter {

  var position: Int = 0
  var adapterPresenter: QuestionsAdapterPresenter? = null
  var view: View? = null

  fun bind() {
    var question = adapterPresenter?.getQuestion(position)
    view?.setTitle(question?.title)
    view?.setQuestionUrl(question?.questionUrl)
  }

  interface View : ViewHolderPresenter.View {
    fun setTitle(title: String?)
    fun setQuestionUrl(questionUrl: String?)
  }
}