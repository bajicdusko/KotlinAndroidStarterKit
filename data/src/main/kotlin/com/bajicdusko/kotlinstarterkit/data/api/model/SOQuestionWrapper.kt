package com.bajicdusko.kotlinstarterkit.data.api.model

import com.bajicdusko.kotlinstarterkit.data.api.model.stackoverflow.SOQuestionData
import com.google.gson.annotations.SerializedName

/**
 * Created by Dusko Bajic on 10.06.17.
 * GitHub @bajicdusko
 */
data class SOQuestionWrapper(
    @SerializedName("items") val questions: List<SOQuestionData>
)