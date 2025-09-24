package com.app.imagerandom.presentation.viewmodel.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.imagerandom.domain.model.ImageRandom
import com.app.imagerandom.domain.usecase.GetListImageRandomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListImageRandomUseCase: GetListImageRandomUseCase,
) : ViewModel() {

    private val _listImageState = MutableStateFlow<List<ImageRandom>>(emptyList())
    val listImageState: StateFlow<List<ImageRandom>> = _listImageState


    init {
        getListImageRandom()
    }

    fun getListImageRandom() {
        viewModelScope.launch {
            val list = getListImageRandomUseCase()
            _listImageState.value = list
        }
    }
}
