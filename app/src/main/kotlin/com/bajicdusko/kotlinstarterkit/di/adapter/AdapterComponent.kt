package com.bajicdusko.kotlinstarterkit.di.adapter

import com.bajicdusko.kotlinstarterkit.ui.questions.QuestionViewHolder
import com.bajicdusko.kotlinstarterkit.ui.questions.QuestionsAdapter
import dagger.Subcomponent

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */

@Subcomponent(modules = arrayOf(AdapterModule::class))
interface AdapterComponent {

  @Subcomponent.Builder
  interface Builder {
    fun adapterModule(adapterModule: AdapterModule): Builder
    fun build(): AdapterComponent
  }

  fun inject(questionsAdapter: QuestionsAdapter)
  fun inject(questionViewHolder: QuestionViewHolder)
}