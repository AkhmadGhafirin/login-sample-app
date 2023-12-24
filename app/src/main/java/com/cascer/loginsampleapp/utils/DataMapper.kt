package com.cascer.loginsampleapp.utils

import com.cascer.loginsampleapp.data.remote.response.LoginResponse
import com.cascer.loginsampleapp.data.remote.response.UserResponse
import com.cascer.loginsampleapp.domain.model.Login
import com.cascer.loginsampleapp.domain.model.User

object DataMapper {
    fun LoginResponse.toDomain() = Login(
        token = token.orEmpty()
    )

    fun UserResponse.toDomain() = User(
        avatar = avatar.orEmpty(),
        email = email.orEmpty(),
        firstName = firstName.orEmpty(),
        id = id ?: 0,
        lastName = lastName.orEmpty()
    )

    fun emptyUser() = User(
        avatar = "", email = "", firstName = "", id = 0, lastName = ""
    )

    fun emptyLogin() = Login(
        token = ""
    )
}