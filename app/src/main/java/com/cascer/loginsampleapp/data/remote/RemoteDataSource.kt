package com.cascer.loginsampleapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cascer.loginsampleapp.data.remote.network.ApiResponse
import com.cascer.loginsampleapp.data.remote.network.ApiService
import com.cascer.loginsampleapp.data.remote.request.LoginRequest
import com.cascer.loginsampleapp.domain.model.User
import com.cascer.loginsampleapp.utils.DataMapper.toDomain
import com.cascer.loginsampleapp.utils.ExceptionUtil.toException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun login(request: LoginRequest): Flow<ApiResponse<String>> {
        return flow {
            try {
                val response = apiService.login(request)
                if (response.isSuccessful) {
                    if (response.body()?.token?.isNotEmpty() == true) {
                        emit(ApiResponse.Success(response.body()?.token.orEmpty()))
                    } else {
                        emit(ApiResponse.Error(response.body()?.token.orEmpty()))
                    }
                } else {
                    emit(ApiResponse.Error(response.errorBody().toException().message.orEmpty()))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getUsers(): PagingSource<Int, User> {
        return object : PagingSource<Int, User>() {
            override fun getRefreshKey(state: PagingState<Int, User>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
                return try {
                    val position = params.key ?: INITIAL_PAGE_INDEX
                    val responseData = apiService.getUsers(page = position)

                    LoadResult.Page(data = responseData.data?.map { it.toDomain() } ?: listOf(),
                        prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                        nextKey = if (responseData.data.isNullOrEmpty()) null else position + 1)
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}