package com.app.imagerandom.presentation.viewmodel.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.imagerandom.domain.model.ImageRandom
import com.app.imagerandom.domain.usecase.GetListImageRandomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListImageRandomUseCase: GetListImageRandomUseCase,
) : ViewModel() {

    private val _listImageState = MutableStateFlow<List<ImageRandom>>(emptyList())
    val listImageState: StateFlow<List<ImageRandom>> = _listImageState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _allImagesLoaded = MutableStateFlow(false)
    val allImagesLoaded: StateFlow<Boolean> = _allImagesLoaded.asStateFlow()

    private var currentPage = 1

    init {
        loadMoreImages()
    }

    fun loadMoreImages() {
        if (_isLoading.value || _allImagesLoaded.value) {
            return // Don't load if already loading or all images are loaded
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newImages = getListImageRandomUseCase(page = currentPage, perPage = ITEMS_PER_PAGE)
                if (newImages.isNotEmpty()) {
                    _listImageState.value = _listImageState.value + newImages
                    currentPage++
                } else {
                    _allImagesLoaded.value = true
                }
            } catch (e: Exception) {
                // Handle error (e.g., show a toast or log)
                // For now, we'll just print it
                e.printStackTrace()
                // You might want to set an error state here to show in the UI
            } finally {
                _isLoading.value = false
            }
        }
    }

    companion object {
        private const val ITEMS_PER_PAGE = 20 // Number of items to load per page
    }
}
