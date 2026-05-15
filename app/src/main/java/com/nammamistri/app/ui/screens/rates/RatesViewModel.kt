package com.nammamistri.app.ui.screens.rates

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MaterialRate(
    val id: Int,
    val name: String,
    val unit: String,
    val rate: String,
    val icon: String
)

class RatesViewModel : ViewModel() {
    private val _rates = MutableStateFlow(
        listOf(
            MaterialRate(1, "Brick", "per piece", "8", "🧱"),
            MaterialRate(2, "Cement", "per bag (50kg)", "380", "🪣"),
            MaterialRate(3, "Sand", "per load", "2500", "⛱"),
            MaterialRate(4, "Steel", "per kg", "70", "🔩"),
            MaterialRate(5, "Gravel", "per load", "3000", "🪨"),
            MaterialRate(6, "Labour", "per day", "500", "👷"),
            MaterialRate(7, "Mason", "per day", "800", "🏗"),
            MaterialRate(8, "Timber", "per cft", "90", "🪵")
        )
    )
    val rates: StateFlow<List<MaterialRate>> = _rates.asStateFlow()

    fun updateRate(id: Int, newRate: String) {
        _rates.value = _rates.value.map {
            if (it.id == id) it.copy(rate = newRate) else it
        }
    }

    fun addRate(name: String, unit: String, rate: String) {
        if (name.isBlank()) return
        val newId = (_rates.value.maxOfOrNull { it.id } ?: 0) + 1
        _rates.value = _rates.value + MaterialRate(newId, name, unit, rate, "📦")
    }

    fun deleteRate(id: Int) {
        _rates.value = _rates.value.filter { it.id != id }
    }
}