package com.cascer.loginsampleapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cascer.loginsampleapp.data.remote.RemoteDataSource
import com.cascer.loginsampleapp.data.remote.network.ApiResponse
import com.cascer.loginsampleapp.data.remote.request.LoginRequest
import com.cascer.loginsampleapp.domain.model.Login
import com.cascer.loginsampleapp.domain.model.User
import com.cascer.loginsampleapp.domain.repository.UserRepository
import com.cascer.loginsampleapp.utils.DataMapper.emptyLogin
import com.cascer.loginsampleapp.utils.DataMapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : UserRepository {
    override fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { remoteDataSource.getUsers() }
        ).flow
    }

    override fun login(email: String, password: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.login(LoginRequest(email, password)).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data))
            }

            is ApiResponse.Empty -> {
                emit(Resource.Success(""))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    private companion object {
        const val PAGE_SIZE = 16
    }
}