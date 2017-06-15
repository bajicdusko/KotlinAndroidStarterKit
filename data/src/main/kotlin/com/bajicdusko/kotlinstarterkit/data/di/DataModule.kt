package com.bajicdusko.kotlinstarterkit.data.di

import dagger.Module

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
@Module(includes = arrayOf(ApiModule::class, RepositoryModule::class))
class DataModule {
}