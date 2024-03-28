package com.raihan.githubapp.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_favorite")
@Parcelize
data class UserFavoriteEntity(
	@PrimaryKey(autoGenerate = false)
	val username: String = "",
	val avatarUrl: String? = null
) : Parcelable