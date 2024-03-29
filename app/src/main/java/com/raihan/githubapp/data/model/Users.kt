package com.raihan.githubapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Users(
  @Json(name = "items") val items: List<UserItems?>? = null
)

@JsonClass(generateAdapter = true)
data class UserItems(
  @Json(name = "login") val login: String? = null,
  @Json(name = "avatar_url") val avatarUrl: String? = null,
)
