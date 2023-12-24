package com.cascer.loginsampleapp.domain.usecase

import androidx.paging.PagingData
import com.cascer.loginsampleapp.data.Resource
import com.cascer.loginsampleapp.domain.model.Login
import com.cascer.loginsampleapp.domain.model.User
import com.cascer.loginsampleapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserUseCase {
    override fun getUsers(): Flow<PagingData<User>> {
        return userRepository.getUsers()
    }

    override fun login(email: String, password: String): Flow<Resource<String>> {
        return userRepository.login(email, password)
    }
}