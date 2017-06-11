package com.bajicdusko.kotlinstarterkit.data.api.model.stackoverflow

import com.google.gson.annotations.SerializedName

/**
 * Created by Dusko Bajic on 10.06.17.
 * GitHub @bajicdusko
 */
data class OwnerData(
        val reputation: Int,
        val userId: Long,
        @SerializedName("profile_image") val avatar: String,
        val displayName: String,
        @SerializedName("link") val profileUrl: String)