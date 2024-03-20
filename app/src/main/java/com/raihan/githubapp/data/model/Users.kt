package com.raihan.githubapp.data.model

import androidx.lifecycle.GeneratedAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Users(
    @Json(name = "total_count") val totalCount: Int? = null,
    @Json(name = "incomplete_results") val incompleteResults: Boolean? = null,
    @Json(name = "items") val items: List<UserItems?>? = null
)

@JsonClass(generateAdapter = true)
data class UserItems(
    @Json(name = "gists_url") val gistsUrl: String? = null,
    @Json(name = "repos_url") val reposUrl: String? = null,
    @Json(name = "following_url") val followingUrl: String? = null,
    @Json(name = "starred_url") val starredUrl: String? = null,
    @Json(name = "login") val login: String? = null,
    @Json(name = "followers_url") val followersUrl: String? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "subscriptions_url") val subscriptionsUrl: String? = null,
    @Json(name = "score") val score: Any? = null,
    @Json(name = "received_events_url") val receivedEventsUrl: String? = null,
    @Json(name = "avatar_url") val avatarUrl: String? = null,
    @Json(name = "events_url") val eventsUrl: String? = null,
    @Json(name = "html_url") val htmlUrl: String? = null,
    @Json(name = "site_admin") val siteAdmin: Boolean? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "gravatar_id") val gravatarId: String? = null,
    @Json(name = "node_id") val nodeId: String? = null,
    @Json(name = "organizations_url") val organizationsUrl: String? = null
)
