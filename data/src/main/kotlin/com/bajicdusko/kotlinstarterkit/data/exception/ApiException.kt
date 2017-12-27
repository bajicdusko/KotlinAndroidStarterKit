package com.bajicdusko.kotlinstarterkit.data.exception

import android.text.TextUtils
import com.bajicdusko.kotlinstarterkit.domain.NETWORK_EXCEPTION_CODE
import com.bajicdusko.kotlinstarterkit.domain.UNKNOWN_EXCEPTION_CODE
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException


/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
class ApiException(private val response: Response<*>?, private val code: Int, val exceptionMessage: String?,
    private val retrofit: Retrofit?, val type: RetrofitErrorTypeEnum) : Exception() {

  init {
    //This is a chance to implement error body conversion in case of HTTP aka 4xx error code
//        if (type === RetrofitErrorTypeEnum.HTTP && retrofit != null && response != null) {
//            try {
//                val converter = retrofit.responseBodyConverter(BaseModel::class.java, arrayOfNulls<Annotation>(0))
//                errorModel = converter.convert(response.errorBody())
//            } catch (e: IOException) {
//            }
//
//        }
  }

  companion object {
    fun httpException(response: Response<*>, retrofit: Retrofit?) = ApiException(response, response.code(),
        response.message(), retrofit, RetrofitErrorTypeEnum.HTTP)

    fun networkException(ioException: IOException) = ApiException(null, NETWORK_EXCEPTION_CODE, ioException.message,
        null, RetrofitErrorTypeEnum.NETWORK)

    fun unknownException(throwable: Throwable): ApiException {
      var message: String?
      if (TextUtils.isEmpty(throwable.message)) {
        message = throwable.javaClass.name
      } else {
        message = throwable.message
      }
      return ApiException(null, UNKNOWN_EXCEPTION_CODE, message, null, RetrofitErrorTypeEnum.UNKNOWN)
    }
  }
}

enum class RetrofitErrorTypeEnum {
  HTTP, NETWORK, UNKNOWN
}
