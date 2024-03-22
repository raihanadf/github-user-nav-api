package com.raihan.githubapp.data.network

import com.raihan.githubapp.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class ApiConfig {
	companion object {
		const val TOKEN = BuildConfig.API_KEY
		const val BASE_URL = BuildConfig.API_URL
		fun getGithubService(): ApiGithubService {
			val authInterceptor = Interceptor { chain ->
				val req = chain.request()
				val requestHeaders = req.newBuilder()
					.addHeader("Authorization", "$TOKEN")
					.build()
				chain.proceed(requestHeaders)
			}
			val client = OkHttpClient.Builder()
				.addInterceptor(authInterceptor)
				.build()
			val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
			val retrofit = Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.client(client)
				.build()

			return retrofit.create(ApiGithubService::class.java)
		}
	}
}