package com.cascer.loginsampleapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cascer.loginsampleapp.domain.usecase.UserUseCase
import com.cascer.loginsampleapp.utils.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val appPreferences: AppPreferences
) : ViewModel() {
    fun login(email: String, password: String) = userUseCase.login(email, password).asLiveData()
    val getToken = appPreferences.getToken().asLiveData()
    fun saveToken(token: String) = viewModelScope.launch {
        appPreferences.saveToken(token)
    }
}