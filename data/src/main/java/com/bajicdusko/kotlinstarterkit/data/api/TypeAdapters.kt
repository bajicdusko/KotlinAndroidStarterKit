package com.bajicdusko.kotlinstarterkit.data.api

import com.bajicdusko.kotlinstarterkit.data.exception.DateParsingException
import com.bajicdusko.kotlinstarterkit.data.toDateTimeFromMillis
import com.bajicdusko.kotlinstarterkit.data.toDefaultString
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import org.joda.time.DateTime

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
class BooleanTypeAdapter : TypeAdapter<Boolean>() {
    override fun write(out: JsonWriter?, value: Boolean?) {
        out?.value(value) ?: out?.nullValue()
    }

    override fun read(jsonReader: JsonReader?): Boolean {
        val jsonToken = jsonReader?.peek()
        return when (jsonToken) {
            JsonToken.BOOLEAN -> jsonReader.nextBoolean()
            JsonToken.NULL -> {
                jsonReader.nextNull()
                return false
            }
            JsonToken.NUMBER -> jsonReader.nextInt() != 0
            JsonToken.STRING -> jsonReader.nextString().toBoolean()
            else -> {
                throw IllegalStateException("Value could not be converted to Boolean. Token: $jsonToken.toString()")
            }
        }
    }
}

class DateTimeAdapter : TypeAdapter<DateTime>() {
    override fun write(out: JsonWriter?, value: DateTime?) {
        out?.nullValue() ?: out?.value(value?.toDefaultString())
    }

    override fun read(`in`: JsonReader?): DateTime {
        var millis = DateTime.now().millis
        var jsonToken = `in`?.peek()
        if (jsonToken == JsonToken.NUMBER) {
            try {
                return millis.toDateTimeFromMillis(1000) //nanos used used on StackOverflow
            } catch(e: Exception) {
                throw DateParsingException(millis, e)
            }
        }

        throw DateParsingException("Excpected a number value. Instead $jsonToken used.")
    }
}