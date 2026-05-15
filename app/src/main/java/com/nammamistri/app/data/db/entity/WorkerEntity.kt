package com.nammamistri.app.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workers",
    foreignKeys = [
        ForeignKey(
            entity = SiteEntity::class,
            parentColumns = ["id"],
            childColumns = ["siteId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("siteId")]
)
data class WorkerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val siteId: Long,
    val name: String,
    val dailyWage: Double,
    val daysPresent: Int = 0,
    val advancePaid: Double = 0.0
) {
    // Computed property — total earned
    val totalEarned: Double get() = dailyWage * daysPresent
    // Balance due to worker
    val balanceDue: Double get() = totalEarned - advancePaid
}