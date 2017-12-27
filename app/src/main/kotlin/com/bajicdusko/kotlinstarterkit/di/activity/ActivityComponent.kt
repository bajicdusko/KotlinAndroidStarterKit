package com.bajicdusko.kotlinstarterkit.di.activity

import com.bajicdusko.kotlinstarterkit.di.adapter.AdapterComponent
import com.bajicdusko.kotlinstarterkit.ui.HomeActivity
import com.bajicdusko.kotlinstarterkit.ui.fragment.auth.LoginFragment
import com.bajicdusko.kotlinstarterkit.ui.questions.QuestionsFragment
import dagger.Subcomponent

/**
 * Created by Dusko Bajic on 29.05.17.
 * GitHub @bajicdusko
 */
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

  fun adapterBuilder(): AdapterComponent.Builder

  @Subcomponent.Builder
  interface Builder {

    fun activityModule(activityModule: ActivityModule): Builder
    fun build(): ActivityComponent
  }

  fun inject(homeActivity: HomeActivity)
  fun inject(loginFragment: LoginFragment)
  fun inject(questionsFragment: QuestionsFragment)
}