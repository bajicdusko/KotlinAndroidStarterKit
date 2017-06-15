package com.bajicdusko.kotlinstarterkit.data.api.model.stackoverflow

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

/**
 * Created by Dusko Bajic on 10.06.17.
 * GitHub @bajicdusko
 */
data class SOQuestionData(
        val tags: List<String>?,
        @SerializedName("owner") val ownerData: OwnerData,
        val isAnswered: Boolean,
        val viewCount: Int,
        val answerCount: Int,
        val score: Int,
        val lastActivityDate: DateTime?,
        val lastEditDate: DateTime?,
        val creationDate: DateTime?,
        val questionId: Long,
        @SerializedName("link") val questionUrl: String,
        val title: String
)