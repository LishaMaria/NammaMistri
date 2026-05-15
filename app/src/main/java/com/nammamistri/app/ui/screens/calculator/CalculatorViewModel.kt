package com.nammamistri.app.ui.screens.calculator

import androidx.lifecycle.ViewModel
import com.nammamistri.app.utils.ConstructionFormulas
import com.nammamistri.app.utils.MaterialResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CalculatorUiState(
    val length: String = "",
    val height: String = "",
    val thickness: String = "9",
    val result: MaterialResult? = null,
    val brickRate: String = "8",
    val cementRate: String = "380",
    val sandRate: String = "2500",
    val errorMessage: String? = null
)

class CalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    fun onLengthChange(value: String) {
        _uiState.value = _uiState.value.copy(length = value, errorMessage = null, result = null)
    }
    fun onHeightChange(value: String) {
        _uiState.value = _uiState.value.copy(height = value, errorMessage = null, result = null)
    }
    fun onThicknessChange(value: String) {
        _uiState.value = _uiState.value.copy(thickness = value, errorMessage = null, result = null)
    }
    fun onBrickRateChange(value: String) {
        _uiState.value = _uiState.value.copy(brickRate = value)
    }
    fun onCementRateChange(value: String) {
        _uiState.value = _uiState.value.copy(cementRate = value)
    }
    fun onSandRateChange(value: String) {
        _uiState.value = _uiState.value.copy(sandRate = value)
    }

    fun calculate() {
        val state = _uiState.value
        val length = state.length.toDoubleOrNull()
        val height = state.height.toDoubleOrNull()
        val thickness = state.thickness.toDoubleOrNull()

        if (length == null || length <= 0) {
            _uiState.value = state.copy(errorMessage = "Enter a valid length")
            return
        }
        if (height == null || height <= 0) {
            _uiState.value = state.copy(errorMessage = "Enter a valid height")
            return
        }
        if (thickness == null || thickness <= 0) {
            _uiState.value = state.copy(errorMessage = "Enter a valid thickness")
            return
        }

        val result = ConstructionFormulas.calculateMaterials(length, height, thickness)
        _uiState.value = state.copy(result = result, errorMessage = null)
    }

    fun reset() {
        _uiState.value = CalculatorUiState()
    }

    fun totalCost(result: MaterialResult): Double {
        val state = _uiState.value
        val brickCost = result.bricks * (state.brickRate.toDoubleOrNull() ?: 0.0)
        val cementCost = result.cementBags * (state.cementRate.toDoubleOrNull() ?: 0.0)
        val sandCost = result.sandLoads * (state.sandRate.toDoubleOrNull() ?: 0.0)
        return brickCost + cementCost + sandCost
    }
}