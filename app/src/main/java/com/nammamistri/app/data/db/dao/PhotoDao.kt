package com.nammamistri.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nammamistri.app.data.db.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: PhotoEntity)

    @Query("SELECT * FROM photos WHERE siteId = :siteId ORDER BY takenAt DESC")
    fun getPhotosBySite(siteId: Long): Flow<List<PhotoEntity>>

    @Query("DELETE FROM photos WHERE uriString = :uriString AND siteId = :siteId")
    suspend fun deletePhoto(uriString: String, siteId: Long)
}