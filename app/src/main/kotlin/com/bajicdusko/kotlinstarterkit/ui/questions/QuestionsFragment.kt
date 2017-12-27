package com.bajicdusko.kotlinstarterkit.ui.questions

import android.os.Bundle
import android.view.View
import butterknife.BindView
import com.bajicdusko.kotlinstarterkit.R
import com.bajicdusko.kotlinstarterkit.domain.model.SOQuestion
import com.bajicdusko.kotlinstarterkit.presenter.questions.QuestionsPresenter
import com.bajicdusko.kotlinstarterkit.ui.fragment.BaseFragment
import com.bajicdusko.kotlinstarterkit.ui.view.DefaultRecyclerView
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
class QuestionsFragment : BaseFragment(), QuestionsPresenter.View {

  companion object {
    fun newInstance() = QuestionsFragment()
  }

  @Inject lateinit var questionsPresenter: QuestionsPresenter

  @BindView(R.id.fragment_question_rv_main) lateinit var rvMain: DefaultRecyclerView

  private val FRAGMENT_NAME = "Questions"
  private val questionsAdapter by lazy { QuestionsAdapter(injector) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true
    injector.inject(this)
    questionsPresenter.view = this
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    questionsPresenter.onSaveInstanceState(outState)
    questionsAdapter.questionsAdapterPresenter.onSaveInstanceState(outState)

  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    questionsPresenter.onRestoreInstanceState(savedInstanceState)
    questionsAdapter.questionsAdapterPresenter.onRestoreInstanceState(savedInstanceState)
  }

  override fun onStart() {
    super.onStart()
    if (isNewInstance) questionsPresenter.loadQuestions() else questionsPresenter.restore()
  }

  override fun restore() {
    questionsAdapter.restore()
  }

  override fun showData(list: List<SOQuestion?>?) {
    if (rvMain.adapter == null) {
      rvMain.adapter = questionsAdapter
    }

    questionsAdapter.questionsAdapterPresenter.onDataChange(list)
  }

  override fun getFragmentName(): String = FRAGMENT_NAME

  override fun setTitle(): Unit? = fragmentChannel?.setToolbarTitle(R.string.questions)

  override fun dispose() = questionsPresenter.dispose()

  override fun getLayoutId(): Int = R.layout.fragment_questions
}

