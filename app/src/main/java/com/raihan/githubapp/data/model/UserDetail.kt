package com.raihan.githubapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetail(

  @Json(name = "login")
  val login: String? = null,

  @Json(name = "followers")
  val followers: Int? = null,

  @Json(name = "avatar_url")
  val avatarUrl: String? = null,

  @Json(name = "following")
  val following: Int? = null,

  @Json(name = "name")
  val name: Any? = null,
)
