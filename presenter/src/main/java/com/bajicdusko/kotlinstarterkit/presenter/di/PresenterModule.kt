package com.bajicdusko.kotlinstarterkit.presenter.di

import com.bajicdusko.kotlinstarterkit.data.di.ApiModule
import com.bajicdusko.kotlinstarterkit.data.di.DataModule
import dagger.Module

/**
 * Created by Dusko Bajic on 07.06.17.
 * GitHub @bajicdusko
 */
@Module(includes = arrayOf(DataModule::class))
class PresenterModule