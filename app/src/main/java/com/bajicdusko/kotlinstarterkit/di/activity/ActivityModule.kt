package com.bajicdusko.kotlinstarterkit.di.activity

import android.content.Context
import android.view.LayoutInflater
import com.bajicdusko.kotlinstarterkit.di.adapter.AdapterComponent
import com.bajicdusko.kotlinstarterkit.domain.DIConstants
import com.bajicdusko.kotlinstarterkit.presenter.di.PresenterModule
import com.bajicdusko.kotlinstarterkit.ui.BaseActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Dusko Bajic on 29.05.17.
 * GitHub @bajicdusko
 */
@ActivityScope
@Module(includes = arrayOf(PresenterModule::class), subcomponents = arrayOf(AdapterComponent::class))
class ActivityModule(private val baseActivity: BaseActivity) {

    @Provides @Named(DIConstants.ACTIVITY) fun provideActivityContext(): Context = baseActivity

    @Provides fun provideLayoutInflater(): LayoutInflater = baseActivity.layoutInflater
}