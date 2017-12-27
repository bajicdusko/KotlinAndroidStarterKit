package com.bajicdusko.kotlinstarterkit.di.app

import com.bajicdusko.kotlinstarterkit.MyApplication
import com.bajicdusko.kotlinstarterkit.di.activity.ActivityComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dusko Bajic on 28.05.17.
 * GitHub @bajicdusko
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, AndroidModule::class))
interface ApplicationComponent {

  fun activityBuilder(): ActivityComponent.Builder

  fun inject(app: MyApplication)
}

