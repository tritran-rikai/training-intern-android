package com.app.imagerandom.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.imagerandom.domain.usecase.auth.CreateSessionTokenUseCase
import com.app.imagerandom.domain.usecase.auth.GetRequestTokenUseCase
import com.app.imagerandom.domain.usecase.auth.ValidateRequestTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getTokenUseCase: GetRequestTokenUseCase,
    private val validateRequestTokenUseCase: ValidateRequestTokenUseCase,
    private val createSessionUseCase: CreateSessionTokenUseCase
) : ViewModel() {

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchToken() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val result = getTokenUseCase()
                _token.value = result.requestToken
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _loading.value = false
            }
        }
    }

    // Xac thuc request token
    private val _isValidRequestToken = MutableLiveData<Boolean>()
    val isValidRequestToken: LiveData<Boolean> = _isValidRequestToken

    fun validateRequestToken(requestToken: String) {
        viewModelScope.launch {
            try {
                val result = validateRequestTokenUseCase(requestToken)
                _isValidRequestToken.value = result.isSuccess
            } catch (_: Exception) { }
        }
    }

    // Tao session
    private val _sessionId = MutableLiveData<String>()
    val sessionId: LiveData<String> = _sessionId
    fun createSession(requestToken: String) {
        viewModelScope.launch {
            try {
                val result = createSessionUseCase(requestToken)
                _sessionId.value = result.sessionId
            } catch (_: Exception) { }
        }
    }
}
