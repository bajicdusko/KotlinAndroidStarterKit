package com.bajicdusko.kotlinstarterkit.ui.questions

import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.bajicdusko.kotlinstarterkit.R
import com.bajicdusko.kotlinstarterkit.di.adapter.AdapterComponent
import com.bajicdusko.kotlinstarterkit.presenter.questions.QuestionViewHolderPresenter
import com.bajicdusko.kotlinstarterkit.presenter.questions.QuestionsAdapterPresenter
import com.bajicdusko.kotlinstarterkit.ui.adapter.BaseViewHolder
import com.bajicdusko.kotlinstarterkit.ui.asHtml
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
class QuestionViewHolder(view: View, adapterComponent: AdapterComponent, questionsAdapterPresenter: QuestionsAdapterPresenter) :
        BaseViewHolder(view), QuestionViewHolderPresenter.View {

    companion object {
        val LAYOUT_ID = R.layout.item_view_question
    }

    @Inject lateinit var questionViewHolderPresenter: QuestionViewHolderPresenter

    @BindView(R.id.item_view_question_tv_title) lateinit var tvTitle: TextView
    @BindView(R.id.item_view_question_tv_url) lateinit var tvQuestionIUrl: TextView

    init {
        adapterComponent.inject(this)
        questionViewHolderPresenter.view = this
        questionViewHolderPresenter.adapterPresenter = questionsAdapterPresenter
    }

    override fun setTitle(title: String?) {
        tvTitle.text = title.asHtml()
    }

    override fun setQuestionUrl(questionUrl: String?) {
        tvQuestionIUrl.text = questionUrl
    }
}