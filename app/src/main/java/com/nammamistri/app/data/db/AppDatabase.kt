package com.nammamistri.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nammamistri.app.data.db.dao.PaymentDao
import com.nammamistri.app.data.db.dao.PhotoDao
import com.nammamistri.app.data.db.dao.SiteDao
import com.nammamistri.app.data.db.dao.WorkerDao
import com.nammamistri.app.data.db.entity.PaymentEntity
import com.nammamistri.app.data.db.entity.PhotoEntity
import com.nammamistri.app.data.db.entity.SiteEntity
import com.nammamistri.app.data.db.entity.WorkerEntity

@Database(
    entities = [
        SiteEntity::class,
        WorkerEntity::class,
        PaymentEntity::class,
        PhotoEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun siteDao(): SiteDao
    abstract fun workerDao(): WorkerDao
    abstract fun paymentDao(): PaymentDao
    abstract fun photoDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "namma_mistri_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}