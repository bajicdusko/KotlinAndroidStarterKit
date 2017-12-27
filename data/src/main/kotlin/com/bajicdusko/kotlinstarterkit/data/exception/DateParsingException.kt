package com.bajicdusko.kotlinstarterkit.data.exception

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
class DateParsingException(message: String, ex: Exception?) : Exception(message, ex) {
  constructor(millis: Long?, ex: Exception) : this(millis.toString(), ex)
  constructor(message: String) : this(message, null)
}