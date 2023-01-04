package com.kudos.trackbitcoin.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kudos.trackbitcoin.db.entity.BitcoinRateDB
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackBitcoinDao {

    @Insert
    suspend fun insertBitcoinRate(bitcoinRateDB: BitcoinRateDB)

    @Query("SELECT * FROM bitcoinRates ORDER BY id DESC LIMIT 1")
    fun getLatestBitcoinRates(): Flow<List<BitcoinRateDB>>

}