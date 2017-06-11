package com.bajicdusko.kotlinstarterkit.di.app

import com.bajicdusko.kotlinstarterkit.MyApplication
import com.bajicdusko.kotlinstarterkit.di.activity.ActivityComponent
import com.bajicdusko.kotlinstarterkit.domain.DIConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Module(subcomponents = arrayOf(ActivityComponent::class))
class ApplicationModule(val app: MyApplication) {

    @Singleton @Provides @Named(DIConstants.APPLICATION) fun provideAppContext() = app
}