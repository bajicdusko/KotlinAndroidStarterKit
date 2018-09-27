package com.bajicdusko.kotlinstarterkit.ui.questions

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bajicdusko.kotlinstarterkit.di.activity.ActivityComponent
import com.bajicdusko.kotlinstarterkit.presenter.questions.QuestionsAdapterPresenter
import com.bajicdusko.kotlinstarterkit.ui.adapter.BaseRecyclerViewAdapter
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
class QuestionsAdapter(activityComponent: ActivityComponent) :
    BaseRecyclerViewAdapter<QuestionViewHolder>(activityComponent), QuestionsAdapterPresenter.View {

  @Inject lateinit var inflater: LayoutInflater
  @Inject lateinit var questionsAdapterPresenter: QuestionsAdapterPresenter

  init {
    injector.inject(this)
    questionsAdapterPresenter.view = this
  }

  override fun dispose() {
    questionsAdapterPresenter.dispose()
  }

  override fun restore() {
    questionsAdapterPresenter.restore()
  }

  override fun notifyAdapter() {
    notifyDataSetChanged()
  }

  override fun onBindViewHolder(viewHolder: QuestionViewHolder, position: Int) {
    viewHolder.questionViewHolderPresenter?.position = position
    viewHolder.questionViewHolderPresenter?.bind()
  }

  override fun getItemCount(): Int = questionsAdapterPresenter.getCount()

  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): QuestionViewHolder =
      QuestionViewHolder(
          inflater.inflate(QuestionViewHolder.LAYOUT_ID, viewGroup, false),
          injector,
          questionsAdapterPresenter
      )
}