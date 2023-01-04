package com.kudos.trackbitcoin.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.kudos.trackbitcoin.db.entity.BitcoinRateDB
import com.kudos.trackbitcoin.repository.TrackBitcoinRepository
import com.kudos.trackbitcoin.utils.clearNotifications
import com.kudos.trackbitcoin.utils.makeStatusNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class TrackBitcoinWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val trackBitcoinRepository: TrackBitcoinRepository
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {

            makeStatusNotification(
                "Bitcoin Rate Tracker",
                "Fetching latest rates...",
                applicationContext
            )
            val apiResponse = trackBitcoinRepository.getBitcoinData()
            val bitcoinRateDB = BitcoinRateDB(
                usdPrice = apiResponse.bpi.USD.rate,
                ukPrice = apiResponse.bpi.GBP.rate,
                euroPrice = apiResponse.bpi.EUR.rate
            )
            trackBitcoinRepository.insertBitcoinData(bitcoinRateDB)
            makeStatusNotification(
                "Bitcoin Rate Tracker",
                "Latest rates fetched.",
                applicationContext
            )

            Result.success()
        } catch (e: Exception) {
            Log.d("TAG", "doWork: error - ${e.localizedMessage}")
            clearNotifications(applicationContext)
            Result.failure(workDataOf("workerKey" to e.localizedMessage))
        }
    }
}