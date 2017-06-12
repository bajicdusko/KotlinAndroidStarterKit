package com.bajicdusko.kotlinstarterkit.data.api

import android.text.TextUtils
import android.util.Base64
import com.bajicdusko.kotlinstarterkit.data.BuildConfig
import com.bajicdusko.kotlinstarterkit.data.api.questions.QuestionsApi
import com.bajicdusko.kotlinstarterkit.domain.repository.CacheRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
class ApiFactory(gson: Gson, val cacheRepository: CacheRepository) {

    private val retrofit: Retrofit

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
                .addInterceptor({
                    var request = it.request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/json;charset=utf-8")
                            .addHeader("Accept", "application/json")
                            .build()

                    if (!TextUtils.isEmpty(cacheRepository.username) && !TextUtils.isEmpty(cacheRepository.password)) {
                        val credentialsConcat = "$cacheRepository.username:$cacheRepository.password"
                        val base64Credentials = Base64.encodeToString(credentialsConcat.toByteArray(), Base64.URL_SAFE)

                        request = request.newBuilder()
                                .addHeader("Authorization", "Basic $base64Credentials".replace("\n", ""))
                                .build()
                    }

                    it.proceed(request)
                }).build()
    }

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.env + BuildConfig.domain + BuildConfig.apiVersion)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2ErrorHandlerCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    fun createQuestionsApi(): QuestionsApi = retrofit.create(QuestionsApi::class.java)
}