package com.nammamistri.app.ui.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nammamistri.app.NammaMistriApp
import com.nammamistri.app.data.db.entity.SiteEntity
import com.nammamistri.app.data.repository.SiteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SiteRepository =
        (application as NammaMistriApp).siteRepository

    val sites: StateFlow<List<SiteEntity>> = repository.getAllSites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addSite(name: String, location: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            repository.addSite(name.trim(), location.trim())
        }
    }

    fun deleteSite(site: SiteEntity) {
        viewModelScope.launch {
            repository.deleteSite(site)
        }
    }
}