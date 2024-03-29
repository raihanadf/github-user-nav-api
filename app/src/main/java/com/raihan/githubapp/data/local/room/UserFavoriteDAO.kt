package com.raihan.githubapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.raihan.githubapp.data.local.entity.UserFavoriteEntity

@Dao
interface UserFavoriteDAO {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(user: UserFavoriteEntity)

	@Delete
	suspend fun delete(user: UserFavoriteEntity)

	@Query("select * from user_favorite")
	fun getAllUser(): LiveData<List<UserFavoriteEntity>>

	@Query("select * from user_favorite where username = :username")
	fun getFavoriteByUsername(username: String):
			LiveData<UserFavoriteEntity>
}