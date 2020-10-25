package com.dupat.dupatmovie.data.network.response

import com.dupat.dupatmovie.data.db.entities.UserEntity
import java.util.*

class LoginResponse(
    var is_success: Boolean? = null,
    var message: String? = null,
    var user: UserEntity? = null
)