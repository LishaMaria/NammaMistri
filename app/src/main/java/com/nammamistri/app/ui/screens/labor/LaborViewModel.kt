package com.nammamistri.app.ui.screens.labor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nammamistri.app.NammaMistriApp
import com.nammamistri.app.data.db.entity.WorkerEntity
import com.nammamistri.app.data.repository.WorkerRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class LaborUiState(
    val workers: List<WorkerEntity> = emptyList(),
    val isLoading: Boolean = true
)

class LaborViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WorkerRepository =
        (application as NammaMistriApp).workerRepository

    private val _uiState = MutableStateFlow(LaborUiState())
    val uiState: StateFlow<LaborUiState> = _uiState.asStateFlow()

    fun loadWorkers(siteId: Long) {
        viewModelScope.launch {
            repository.getWorkersBySite(siteId).collect { workers ->
                _uiState.value = LaborUiState(workers = workers, isLoading = false)
            }
        }
    }

    fun addWorker(siteId: Long, name: String, dailyWage: Double) {
        if (name.isBlank() || dailyWage <= 0) return
        viewModelScope.launch {
            repository.addWorker(siteId, name.trim(), dailyWage)
        }
    }

    fun markAttendance(workerId: Long) {
        viewModelScope.launch { repository.markAttendance(workerId) }
    }

    fun addAdvance(workerId: Long, amount: Double) {
        if (amount <= 0) return
        viewModelScope.launch { repository.addAdvance(workerId, amount) }
    }

    fun deleteWorker(worker: WorkerEntity) {
        viewModelScope.launch { repository.deleteWorker(worker) }
    }

    val totalPayable: Double
        get() = _uiState.value.workers.sumOf { it.totalEarned }

    val totalAdvance: Double
        get() = _uiState.value.workers.sumOf { it.advancePaid }

    val totalBalance: Double
        get() = _uiState.value.workers.sumOf { it.balanceDue }
}