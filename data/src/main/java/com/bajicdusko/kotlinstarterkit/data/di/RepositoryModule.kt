package com.bajicdusko.kotlinstarterkit.data.di

import android.content.SharedPreferences
import com.bajicdusko.kotlinstarterkit.data.repository.QuestionRepositoryData
import com.bajicdusko.kotlinstarterkit.domain.repository.QuestionRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
@Module
class RepositoryModule {

    @Provides fun providesQuestionsRepository(): QuestionRepository = QuestionRepositoryData()
}