package com.nammamistri.app.data.repository

import com.nammamistri.app.data.db.dao.WorkerDao
import com.nammamistri.app.data.db.entity.WorkerEntity
import kotlinx.coroutines.flow.Flow

class WorkerRepository(private val workerDao: WorkerDao) {
    fun getWorkersBySite(siteId: Long): Flow<List<WorkerEntity>> =
        workerDao.getWorkersBySite(siteId)

    fun getWorkerById(workerId: Long): Flow<WorkerEntity?> =
        workerDao.getWorkerById(workerId)

    suspend fun addWorker(siteId: Long, name: String, dailyWage: Double): Long {
        return workerDao.insertWorker(
            WorkerEntity(siteId = siteId, name = name, dailyWage = dailyWage)
        )
    }

    suspend fun markAttendance(workerId: Long) = workerDao.markAttendance(workerId)

    suspend fun addAdvance(workerId: Long, amount: Double) =
        workerDao.addAdvance(workerId, amount)

    suspend fun deleteWorker(worker: WorkerEntity) = workerDao.deleteWorker(worker)

    suspend fun updateWorker(worker: WorkerEntity) = workerDao.updateWorker(worker)
}