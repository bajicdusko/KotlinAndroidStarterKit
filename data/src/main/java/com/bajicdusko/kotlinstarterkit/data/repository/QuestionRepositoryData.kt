package com.bajicdusko.kotlinstarterkit.data.repository

import com.bajicdusko.kotlinstarterkit.data.api.model.stackoverflow.SOQuestionData
import com.bajicdusko.kotlinstarterkit.data.api.questions.QuestionsApi
import com.bajicdusko.kotlinstarterkit.data.transform
import com.bajicdusko.kotlinstarterkit.domain.DEFAULT_PAGE
import com.bajicdusko.kotlinstarterkit.domain.DEFAULT_PAGE_SIZE
import com.bajicdusko.kotlinstarterkit.domain.model.SOQuestion
import com.bajicdusko.kotlinstarterkit.domain.repository.QuestionRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 10.06.17.
 * GitHub @bajicdusko
 */

class QuestionRepositoryData @Inject constructor() : QuestionRepository {

    @Inject lateinit var questionsApi: QuestionsApi

    override fun getQuestionsByTag(tag: String): Single<List<SOQuestion?>> {
        return questionsApi.get(DEFAULT_PAGE, DEFAULT_PAGE_SIZE, tag)
                .map { it.questions }
                .flatMap { if (it.isNotEmpty()) Flowable.fromIterable(it) else Flowable.empty() }
                .map { it.transform() }
                .toList()
    }
}