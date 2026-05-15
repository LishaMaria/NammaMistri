package com.nammamistri.app.ui.screens.photos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nammamistri.app.NammaMistriApp
import com.nammamistri.app.data.repository.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotosViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PhotoRepository =
        (application as NammaMistriApp).photoRepository

    private val _photoUris = MutableStateFlow<List<String>>(emptyList())
    val photoUris: StateFlow<List<String>> = _photoUris.asStateFlow()

    private var currentSiteId: Long = -1L

    fun loadPhotos(siteId: Long) {
        currentSiteId = siteId
        viewModelScope.launch {
            repository.getPhotosBySite(siteId).collect { photos ->
                _photoUris.value = photos.map { it.uriString }
            }
        }
    }

    fun addPhoto(uriString: String) {
        if (currentSiteId == -1L) return
        viewModelScope.launch {
            repository.addPhoto(currentSiteId, uriString)
        }
    }

    fun removePhoto(uriString: String) {
        if (currentSiteId == -1L) return
        viewModelScope.launch {
            repository.deletePhoto(uriString, currentSiteId)
        }
    }
}