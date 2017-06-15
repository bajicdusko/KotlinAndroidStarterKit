package com.bajicdusko.kotlinstarterkit.data.api

import com.bajicdusko.kotlinstarterkit.data.exception.ApiException
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

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *> {
        return RxJava2ErrorAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit))
    }

    class RxJava2ErrorAdapterWrapper<R>(private val retrofit: Retrofit?, private val wrapped: CallAdapter<R, *>) : CallAdapter<R, Flowable<*>> {

        override fun adapt(call: Call<R>?): Flowable<*> {
            return (wrapped.adapt(call) as Flowable<*>).onErrorResumeNext(Function {
                val apiException: ApiException
                if (it is HttpException) {
                    apiException = ApiException.httpException(it.response(), retrofit)
                } else if (it is IOException) {
                    apiException = ApiException.networkException(it)
                } else {
                    apiException = ApiException.unknownException(it)
                }

                Flowable.error(apiException)
            })
        }

        override fun responseType(): Type = wrapped.responseType()
    }
}