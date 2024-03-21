package com.raihan.githubapp.data.network

import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.data.model.Users
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val TOKEN = "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"

interface ApiGithubService {
	@Headers("Authorization: token ${TOKEN}")
	@GET("search/users")
	suspend fun searchUser(@Query("q") query: String): Users

	@Headers("Authorization: token ${TOKEN}")
	@GET("users")
	suspend fun getAllUsers(): List<UserItems>
}