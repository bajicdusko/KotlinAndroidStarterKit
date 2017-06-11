package com.bajicdusko.kotlinstarterkit.domain.model

import com.bajicdusko.kotlinstarterkit.domain.EMPTY_STRING

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
data class Owner(
        val reputation: Int,
        val userId: Long,
        val avatar: String,
        val displayName: String,
        val profileUrl: String)