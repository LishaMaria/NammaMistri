package com.nammamistri.app.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nammamistri.app.data.db.entity.WorkerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorker(worker: WorkerEntity): Long

    @Update
    suspend fun updateWorker(worker: WorkerEntity)

    @Delete
    suspend fun deleteWorker(worker: WorkerEntity)

    @Query("SELECT * FROM workers WHERE siteId = :siteId ORDER BY name ASC")
    fun getWorkersBySite(siteId: Long): Flow<List<WorkerEntity>>

    @Query("SELECT * FROM workers WHERE id = :workerId")
    fun getWorkerById(workerId: Long): Flow<WorkerEntity?>

    @Query("UPDATE workers SET daysPresent = daysPresent + 1 WHERE id = :workerId")
    suspend fun markAttendance(workerId: Long)

    @Query("UPDATE workers SET advancePaid = advancePaid + :amount WHERE id = :workerId")
    suspend fun addAdvance(workerId: Long, amount: Double)
}