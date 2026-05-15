package com.nammamistri.app

import android.app.Application
import com.nammamistri.app.data.db.AppDatabase
import com.nammamistri.app.data.repository.PaymentRepository
import com.nammamistri.app.data.repository.PhotoRepository
import com.nammamistri.app.data.repository.SiteRepository
import com.nammamistri.app.data.repository.WorkerRepository

class NammaMistriApp : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val siteRepository by lazy { SiteRepository(database.siteDao()) }
    val workerRepository by lazy { WorkerRepository(database.workerDao()) }
    val paymentRepository by lazy { PaymentRepository(database.paymentDao()) }
    val photoRepository by lazy { PhotoRepository(database.photoDao()) }
}