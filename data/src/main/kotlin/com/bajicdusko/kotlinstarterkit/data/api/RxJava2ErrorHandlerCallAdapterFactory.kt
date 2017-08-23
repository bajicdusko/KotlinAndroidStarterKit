package com.bajicdusko.kotlinstarterkit.data.api

import com.bajicdusko.kotlinstarterkit.data.api.model.ErrorModel
import com.bajicdusko.kotlinstarterkit.data.exception.ApiException
import com.bajicdusko.kotlinstarterkit.data.exception.InternalRxJavaRetrofitAdapterException
import io.reactivex.Flowable
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
class RxJava2ErrorHandlerCallAdapterFactory : CallAdapter.Factory() {

  val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

  override fun get(returnType: Type?, annotations: Array<out Annotation>?,
      retrofit: Retrofit?): CallAdapter<*, *> {
    return RxJava2ErrorAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit))
  }

  class RxJava2ErrorAdapterWrapper<R>(private val retrofit: Retrofit?,
      private val wrapped: CallAdapter<R, *>?) : CallAdapter<R, Flowable<*>> {

    override fun adapt(call: Call<R>?): Flowable<*> {
      return if (wrapped != null) {
        (wrapped.adapt(call) as Flowable<*>).onErrorResumeNext(Function {
          val apiException = when (it) {
            is HttpException -> ApiException.httpException(it.response(), retrofit)
            is IOException -> ApiException.networkException(it)
            else -> ApiException.unknownException(it)
          }

          Flowable.error(apiException)
        })
      } else {
        Flowable.error<R>(ApiException.unknownException(InternalRxJavaRetrofitAdapterException()))
      }
    }

    override fun responseType(): Type = wrapped?.responseType() ?: ErrorModel::class.java.genericSuperclass
  }
}