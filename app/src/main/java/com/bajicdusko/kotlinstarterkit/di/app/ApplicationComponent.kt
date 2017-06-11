package com.bajicdusko.kotlinstarterkit.di.app

import com.bajicdusko.kotlinstarterkit.MyApplication
import com.bajicdusko.kotlinstarterkit.di.activity.ActivityComponent
import com.bajicdusko.kotlinstarterkit.di.activity.ActivityModule
import dagger.Component

/**
 * Created by Dusko Bajic on 28.05.17.
 * GitHub @bajicdusko
 */

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun activityBuilder(): ActivityComponent.Builder

    fun inject(app: MyApplication)
}

