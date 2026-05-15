package com.nammamistri.app.data.repository

import com.nammamistri.app.data.db.dao.SiteDao
import com.nammamistri.app.data.db.entity.SiteEntity
import kotlinx.coroutines.flow.Flow

class SiteRepository(private val siteDao: SiteDao) {
    fun getAllSites(): Flow<List<SiteEntity>> = siteDao.getAllSites()

    fun getSiteById(id: Long): Flow<SiteEntity?> = siteDao.getSiteById(id)

    suspend fun addSite(name: String, location: String): Long {
        return siteDao.insertSite(SiteEntity(name = name, location = location))
    }

    suspend fun deleteSite(site: SiteEntity) = siteDao.deleteSite(site)
}