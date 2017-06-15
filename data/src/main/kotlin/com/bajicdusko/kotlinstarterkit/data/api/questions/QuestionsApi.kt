package com.bajicdusko.kotlinstarterkit.data.api.questions

import com.bajicdusko.kotlinstarterkit.data.api.model.SOQuestionWrapper
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dusko Bajic on 10.06.17.
 * GitHub @bajicdusko
 */
interface QuestionsApi {

    companion object {
        const val QUESTIONS = "questions"
        const val SORTING: String = "?order=desc&sort=activity&site=stackoverflow"
    }

    @GET(QUESTIONS + SORTING) fun get(@Query("page") page: Int,
                                      @Query("pagesize") pageSize: Int,
                                      @Query("tagged") tag: String): Flowable<SOQuestionWrapper>
}