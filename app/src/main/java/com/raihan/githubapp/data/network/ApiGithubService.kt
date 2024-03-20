package com.raihan.githubapp.data.network

import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.data.model.Users
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiGithubService {
    @GET("search/users")
    suspend fun searchUser(@Query("q") query: String): Users

    @GET("users")
    suspend fun getAllUsers(): List<UserItems>
}