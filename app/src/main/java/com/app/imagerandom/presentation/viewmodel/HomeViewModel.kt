package com.app.imagerandom.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.app.imagerandom.data.local.SharedPrefHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedPrefHelper: SharedPrefHelper
) : ViewModel() {

    fun checkAutoSignIn(): Boolean {
        return !sharedPrefHelper.getSessionId().isNullOrEmpty()
    }
}