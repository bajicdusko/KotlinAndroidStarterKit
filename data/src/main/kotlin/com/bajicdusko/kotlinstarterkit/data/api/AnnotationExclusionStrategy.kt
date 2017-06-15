package com.bajicdusko.kotlinstarterkit.data.api

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
class AnnotationExclusionStrategy : ExclusionStrategy {
    override fun shouldSkipClass(clazz: Class<*>?): Boolean = false

    override fun shouldSkipField(f: FieldAttributes?): Boolean = f?.getAnnotation(Exclude::class.java) != null
}