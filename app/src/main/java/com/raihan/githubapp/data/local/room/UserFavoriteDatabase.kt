package com.raihan.githubapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raihan.githubapp.data.local.entity.UserFavoriteEntity

@Database(
	entities = [
		UserFavoriteEntity::class
	], version = 1
)
abstract class UserFavoriteDatabase : RoomDatabase() {
	abstract fun userFavDao(): UserFavoriteDAO

	companion object {
		@Volatile
		private var INSTANCE: UserFavoriteDatabase? = null

		@JvmStatic
		fun getDatabase(context: Context): UserFavoriteDatabase {
			if (INSTANCE == null) {
				synchronized(UserFavoriteDatabase::class.java) {
					INSTANCE = Room.databaseBuilder(
						context.applicationContext,
						UserFavoriteDatabase::class.java, "favorite_database"
					).build()
				}
			}
			return INSTANCE as UserFavoriteDatabase
		}
	}
}