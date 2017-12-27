package com.bajicdusko.kotlinstarterkit.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dusko Bajic on 12.06.17.
 * GitHub @bajicdusko
 */

@Module
@Singleton
class AndroidModule(val context: Context) {

  @Provides
  @Singleton
  fun providesSharedPreferences() = context.getSharedPreferences("private_pref", Context.MODE_PRIVATE)
}