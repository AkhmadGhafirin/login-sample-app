package com.cascer.loginsampleapp.domain.repository

import androidx.paging.PagingData
import com.cascer.loginsampleapp.data.Resource
import com.cascer.loginsampleapp.domain.model.Login
import com.cascer.loginsampleapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<User>>
    fun login(email: String, password: String): Flow<Resource<String>>
}