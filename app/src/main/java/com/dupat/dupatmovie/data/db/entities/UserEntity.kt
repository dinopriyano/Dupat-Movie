package com.dupat.dupatmovie.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

const val CURRENT_ID: Int = 0

@Entity
data class UserEntity(
    var id: Int? = null,
    var name: String? = null,
    var username: String? = null,
    var created_at: String? = null,
    var token: String? = null
)
{
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_ID
}