package com.nammamistri.app.data.repository

import com.nammamistri.app.data.db.dao.PhotoDao
import com.nammamistri.app.data.db.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

class PhotoRepository(private val photoDao: PhotoDao) {
    fun getPhotosBySite(siteId: Long): Flow<List<PhotoEntity>> =
        photoDao.getPhotosBySite(siteId)

    suspend fun addPhoto(siteId: Long, uriString: String) {
        photoDao.insertPhoto(PhotoEntity(siteId = siteId, uriString = uriString))
    }

    suspend fun deletePhoto(uriString: String, siteId: Long) {
        photoDao.deletePhoto(uriString, siteId)
    }
}