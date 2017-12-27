package com.bajicdusko.kotlinstarterkit.domain.model

import org.joda.time.DateTime

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
data class SOQuestion(
    val tags: MutableList<String>?,
    val owner: Owner,
    val isAnswered: Boolean,
    val viewCount: Int,
    val answerCount: Int,
    val score: Int,
    val lastActivityDate: DateTime?,
    val lastEditDate: DateTime?,
    val creationDate: DateTime?,
    val questionId: Long,
    val questionUrl: String,
    val title: String
)