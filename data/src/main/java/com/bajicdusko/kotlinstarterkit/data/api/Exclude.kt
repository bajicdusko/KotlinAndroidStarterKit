package com.bajicdusko.kotlinstarterkit.data.api

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Exclude