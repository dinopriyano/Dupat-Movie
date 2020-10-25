package com.dupat.dupatmovie.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dupat.dupatmovie.data.db.entities.CURRENT_ID
import com.dupat.dupatmovie.data.db.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: UserEntity) : Long

    @Query("SELECT * FROM users WHERE ID = $CURRENT_ID")
    fun getUser(): LiveData<UserEntity>
}