package com.bajicdusko.kotlinstarterkit.data.repository

import android.content.SharedPreferences
import com.bajicdusko.kotlinstarterkit.data.save
import com.bajicdusko.kotlinstarterkit.domain.EMPTY_STRING
import com.bajicdusko.kotlinstarterkit.domain.repository.CacheRepository
import com.google.gson.Gson
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 10.06.17.
 * GitHub @bajicdusko
 */
class CacheRepositoryData @Inject constructor() : CacheRepository {

    @Inject lateinit var sharedPreferences: SharedPreferences
    @Inject lateinit var gson: Gson

    private val KEY_USERNAME = "key_username"
    private val KEY_PASSWORD = "key_password"
    private val KEY_SIDEBAR_SHOWN = "key_sidebar_shown"

    override var username: String
        get() = sharedPreferences.getString(KEY_USERNAME, EMPTY_STRING)
        set(value) {
            sharedPreferences.save(KEY_USERNAME, value)
        }
    override var password: String
        get() = sharedPreferences.getString(KEY_PASSWORD, EMPTY_STRING)
        set(value) {
            sharedPreferences.save(KEY_PASSWORD, value)
        }

    override fun isSidebarShown(): Boolean {
        var isSidebarShown = sharedPreferences.getBoolean(KEY_SIDEBAR_SHOWN, false)
        if (!isSidebarShown) {
            sharedPreferences.save(KEY_SIDEBAR_SHOWN, true)
        }
        return isSidebarShown
    }

    override fun isLoggedIn(): Boolean = username.isNotEmpty() && password.isNotEmpty()

    override fun clear() {
        sharedPreferences.edit().clear()
    }
}