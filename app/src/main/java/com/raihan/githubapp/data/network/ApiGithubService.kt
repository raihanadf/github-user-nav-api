package com.raihan.githubapp.data.network

import com.raihan.githubapp.data.model.UserDetail
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.data.model.Users
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiGithubService {
	@GET("search/users")
	suspend fun searchUser(@Query("q") query: String): Users

	@GET("users")
	suspend fun getAllUsers(): List<UserItems>

	@GET("users/{username}")
	suspend fun getUserDetail(@Path("username") username: String): UserDetail

	@GET("users/{username}/followers")
	suspend fun getFollowers(@Path("username") username: String): List<UserItems>

	@GET("users/{username}/following")
	suspend fun getFollowing(@Path("username") username: String): List<UserItems>


}