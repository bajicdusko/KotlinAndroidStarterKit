package com.bajicdusko.kotlinstarterkit.data

import android.content.SharedPreferences
import com.bajicdusko.kotlinstarterkit.data.api.model.stackoverflow.OwnerData
import com.bajicdusko.kotlinstarterkit.data.api.model.stackoverflow.SOQuestionData
import com.bajicdusko.kotlinstarterkit.data.exception.ApiException
import com.bajicdusko.kotlinstarterkit.domain.model.Owner
import com.bajicdusko.kotlinstarterkit.domain.model.SOQuestion
import com.google.gson.Gson
import io.reactivex.exceptions.CompositeException
import org.joda.time.DateTime

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */

/**
 * DateTime extension functions
 */
const val PATTERN = "yyyy-MM-dd HH:mm:ss"
const val PRETTY_PATTERN = "yyyy-MM-dd HH:mm:ss"
const val TITLE_PATTERN = "dd MM yyyy"

fun DateTime.toDefaultString() = toString(PATTERN)
fun DateTime.toPrettyString() = toString(PRETTY_PATTERN)
fun DateTime.toTitleString() = toString(PRETTY_PATTERN)
fun Long.toDateTimeFromMillis(factor: Int) = DateTime(this * factor)

/**
 * SharedPreferences extension functions
 */
fun SharedPreferences.save(key: String, value: String) = save { putString(key, value) }

fun SharedPreferences.save(key: String, value: Int) = save { putInt(key, value) }
fun SharedPreferences.save(key: String, value: Long) = save { putLong(key, value) }
fun SharedPreferences.save(key: String, value: Boolean) = save { putBoolean(key, value) }
fun SharedPreferences.save(key: String, value: Any, gson: Gson) = save {
    var stringValue = gson.toJson(value)
    putString(key, stringValue)
}

inline fun SharedPreferences.save(body: SharedPreferences.Editor.() -> Unit) {
    val editor = this.edit()
    editor.body()
    editor.apply()
}

/**
 * Mapper extension functions
 */
fun SOQuestion.transform() = with(this, {
    SOQuestionData(
            tags?.toList(),
            owner.transform(),
            isAnswered,
            viewCount,
            answerCount,
            score,
            lastActivityDate,
            lastEditDate,
            creationDate,
            questionId,
            questionUrl,
            title)
})

fun SOQuestionData.transform() = with(this, {
    SOQuestion(
            tags?.toMutableList(),
            ownerData.transform(),
            isAnswered,
            viewCount,
            answerCount,
            score,
            lastActivityDate,
            lastEditDate,
            creationDate,
            questionId,
            questionUrl,
            title)
})

fun Owner.transform() = with(this, {
    OwnerData(reputation, userId, avatar, displayName, profileUrl)
})

fun OwnerData.transform() = with(this, {
    Owner(reputation, userId, avatar, displayName, profileUrl)
})


fun Throwable.getErrorMessage(): String? {
    if (this is ApiException) {
        return exceptionMessage
    } else if (this is CompositeException) {
        return exceptions[0].message
    } else {
        return message
    }
}