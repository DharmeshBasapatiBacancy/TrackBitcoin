package com.kudos.trackbitcoin.repository

import com.kudos.trackbitcoin.db.dao.TrackBitcoinDao
import com.kudos.trackbitcoin.db.entity.BitcoinRateDB
import com.kudos.trackbitcoin.network.models.TrackBitcoinResponseApi
import com.kudos.trackbitcoin.network.service.TrackBitcoinService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrackBitcoinRepository @Inject constructor(
    private val trackBitcoinService: TrackBitcoinService,
    private val trackBitcoinDao: TrackBitcoinDao
) {

    suspend fun getBitcoinData(): TrackBitcoinResponseApi {
        return trackBitcoinService.getBitcoinData()
    }

    suspend fun insertBitcoinData(bitcoinRateDB: BitcoinRateDB) {
        trackBitcoinDao.insertBitcoinRate(bitcoinRateDB)
    }

    fun getLatestBitcoinRates(): Flow<List<BitcoinRateDB>> {
        return trackBitcoinDao.getLatestBitcoinRates()
    }

}