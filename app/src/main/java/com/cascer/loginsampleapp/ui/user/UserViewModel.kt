package com.cascer.loginsampleapp.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cascer.loginsampleapp.domain.usecase.UserUseCase
import com.cascer.loginsampleapp.utils.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val appPreferences: AppPreferences
) : ViewModel() {
    val users = userUseCase.getUsers().cachedIn(viewModelScope).asLiveData()
    fun clearToken() = viewModelScope.launch {
        appPreferences.clearToken()
    }
}