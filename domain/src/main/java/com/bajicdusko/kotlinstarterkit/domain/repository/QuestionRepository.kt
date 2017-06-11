package com.bajicdusko.kotlinstarterkit.domain.repository

import com.bajicdusko.kotlinstarterkit.domain.model.SOQuestion
import io.reactivex.Single

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
interface QuestionRepository {

    fun getQuestionsByTag(tag: String): Single<List<SOQuestion?>>
}