package com.nammamistri.app.data.repository

import com.nammamistri.app.data.db.dao.PaymentDao
import com.nammamistri.app.data.db.entity.PaymentEntity
import kotlinx.coroutines.flow.Flow

class PaymentRepository(private val paymentDao: PaymentDao) {
    fun getPaymentsByWorker(workerId: Long): Flow<List<PaymentEntity>> =
        paymentDao.getPaymentsByWorker(workerId)

    suspend fun addPayment(workerId: Long, amount: Double, note: String) {
        paymentDao.insertPayment(
            PaymentEntity(workerId = workerId, amount = amount, note = note)
        )
    }
}