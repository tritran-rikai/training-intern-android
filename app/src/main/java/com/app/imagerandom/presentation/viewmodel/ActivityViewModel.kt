package com.app.imagerandom.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor() : ViewModel() {
    val TAG = javaClass.simpleName

    private val _navigate = MutableStateFlow<String>("")
    val navigate: StateFlow<String> get() = _navigate

    private val _isFirstLaunch = MutableStateFlow(true)
    val isFirstLaunch: StateFlow<Boolean> get() = _isFirstLaunch


    init {
        Log.d(TAG, "init: ")
    }

    fun updateState(newState: String) {
        _navigate.value = newState
    }
    fun setNotFirstLaunch() {
        _isFirstLaunch.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}