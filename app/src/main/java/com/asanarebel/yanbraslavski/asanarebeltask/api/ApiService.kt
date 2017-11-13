package com.affinitas.task.api

import com.affinitas.task.models.responses.PersonalityTestResponseModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by yan.braslavski on 10/12/17.
 */
interface ApiService {

    @GET("/questions")
    fun fetchData(): Observable<PersonalityTestResponseModel>

    companion object {
        private val SERVER_IP_ADDRESS: String = "localhost"

        fun create(): ApiService {
            val httpClient = createHttpClient()
            val retrofit = Retrofit.Builder()
                    .client(httpClient.build())
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("http://$SERVER_IP_ADDRESS:8080/")
                    .build()

            return retrofit.create(ApiService::class.java)
        }

        private fun createHttpClient(): OkHttpClient.Builder {
            val httpClient = OkHttpClient.Builder()
            val logging = createLoggingInterceptor()
            // add logging as last interceptor
            httpClient.addInterceptor(logging)
            return httpClient
        }

        private fun createLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }
    }
}