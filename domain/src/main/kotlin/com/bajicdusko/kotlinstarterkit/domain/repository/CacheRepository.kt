package com.bajicdusko.kotlinstarterkit.domain.repository

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
interface CacheRepository {

  var username: String
  var password: String

  fun isSidebarShown(): Boolean
  fun isLoggedIn(): Boolean

  fun clear()
}
