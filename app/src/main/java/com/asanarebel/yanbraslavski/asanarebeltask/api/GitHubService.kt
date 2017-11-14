package com.affinitas.task.api

import com.asanarebel.yanbraslavski.asanarebeltask.BuildConfig
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by yan.braslavski on 10/12/17.
 */
interface GitHubService {

    @GET("/users/{username}/repos")
    fun getRepositories(@Path("username") username: String): Observable<List<GithubRepoResponseModel>>

    companion object {

        private val BASE_URL = "https://api.github.com/"

        fun create(): GitHubService {
            val httpClient = createHttpClient()
            val retrofit = Retrofit.Builder()
                    .client(httpClient.build())
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(GitHubService::class.java)
        }

        private fun createHttpClient(): OkHttpClient.Builder {
            val httpClient = OkHttpClient.Builder()
            val logging = createLoggingInterceptor()
            // add logging as last interceptor for better debugging
            httpClient.addInterceptor(logging)
            return httpClient
        }

        private fun createLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return logging
        }
    }
}