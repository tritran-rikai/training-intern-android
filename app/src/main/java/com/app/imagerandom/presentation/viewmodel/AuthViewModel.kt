package com.app.imagerandom.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.imagerandom.data.local.SharedPrefHelper
import com.app.imagerandom.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPrefHelper: SharedPrefHelper
) : ViewModel() {

    private val _token = MutableLiveData<String>()

    private val _loading = MutableLiveData<Boolean>()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isValidRequestToken = MutableLiveData<Boolean>()

    private val _sessionId = MutableLiveData<String>()
    private val sessionId: LiveData<String> = _sessionId

    fun startAuthFlow(username: String, password: String, navigateToHomeScreen: () -> Unit) {
        viewModelScope.launch {
            _loading.value = true
            try {
                // Get request token
                val tokenResult = authUseCase.getRequestToken()
                val requestToken = tokenResult.requestToken
                _token.value = requestToken

                // Validate request token
                val validateResult = authUseCase.validateRequestToken(username, password,requestToken)
                _isValidRequestToken.value = validateResult.isSuccess

                if (validateResult.isSuccess) {
                    // Create session
                    val sessionResult = authUseCase.createSessionToken(requestToken)
                    _sessionId.value = sessionResult.sessionId
                    sharedPrefHelper.saveSessionId(sessionId.value!!)
                    navigateToHomeScreen()
                } else {
                    _error.value = "Token không hợp lệ"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _loading.value = false
            }
        }
    }
}
