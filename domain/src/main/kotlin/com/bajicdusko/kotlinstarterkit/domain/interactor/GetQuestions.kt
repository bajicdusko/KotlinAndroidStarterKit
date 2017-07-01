package com.bajicdusko.kotlinstarterkit.domain.interactor

import com.bajicdusko.kotlinstarterkit.domain.model.SOQuestion
import com.bajicdusko.kotlinstarterkit.domain.repository.QuestionRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
class GetQuestions @Inject constructor() : UseCase<List<SOQuestion?>, String> {

    @Inject lateinit var questionsRepository: QuestionRepository

    override fun execute(tag: String): Single<List<SOQuestion?>> = questionsRepository.getQuestionsByTag(tag)
}