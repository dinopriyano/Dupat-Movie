package com.dupat.dupatmovie.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dupat.dupatmovie.data.db.dao.UserDao
import com.dupat.dupatmovie.data.db.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object{

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(ctx: Context) = instance ?: synchronized(LOCK){
            instance?: buildDatabase(ctx).also {
                instance = it
            }
        }

        private fun buildDatabase(ctx: Context): AppDatabase =
            Room.databaseBuilder(
                ctx?.applicationContext,
                AppDatabase::class.java,
                "DupatMovie.db"
            ).build()


    }
}