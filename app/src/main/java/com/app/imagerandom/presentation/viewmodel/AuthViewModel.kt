package com.app.imagerandom.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.imagerandom.data.local.SharedPrefHelper
import com.app.imagerandom.domain.model.ApiErrorResponse
import com.app.imagerandom.domain.usecase.auth.AuthUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPrefHelper: SharedPrefHelper
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun startAuthFlow(username: String, password: String, navigateToHomeScreen: () -> Unit) {
        viewModelScope.launch {
            try {
                val result = authUseCase.startAuthFlow(username, password)

                if (result.isNotEmpty()) {
                    // Create session
                    sharedPrefHelper.saveSessionId(result)
                    navigateToHomeScreen()
                } else {
                    _error.value = "Token không hợp lệ"
                }
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is HttpException -> {
                        val errorBody = e.response()?.errorBody()?.string()
                        if (!errorBody.isNullOrEmpty()) {
                            try {
                                val errorResponse = Gson().fromJson(errorBody, ApiErrorResponse::class.java)
                                errorResponse.statusMessage ?: "Unknown API error"
                            } catch (ex: Exception) {
                                "Error parsing error response"
                            }
                        } else {
                            "Unknown API error"
                        }
                    }
                    else -> e.message ?: "Unknown error"
                }
                _error.value = errorMessage
            }
        }
    }
}
