package com.raihan.githubapp.data.local

import android.app.Application
import androidx.lifecycle.LiveData
import com.raihan.githubapp.data.local.entity.UserFavoriteEntity
import com.raihan.githubapp.data.local.room.UserFavoriteDAO
import com.raihan.githubapp.data.local.room.UserFavoriteDatabase

class UserFavoriteRepository(application: Application) {
	private val mFavDao: UserFavoriteDAO

	init {
		val db = UserFavoriteDatabase.getDatabase(application)
		mFavDao = db.userFavDao()
	}

	fun getAllFavUser(): LiveData<List<UserFavoriteEntity>> = mFavDao.getAllUser()

	fun getFavorite(username: String): LiveData<UserFavoriteEntity> =
		mFavDao.getFavoriteByUsername(username)


	suspend fun insert(user: UserFavoriteEntity) {
		mFavDao.insert(user)
	}

	suspend fun delete(user: UserFavoriteEntity) {
		mFavDao.delete(user)
	}
}