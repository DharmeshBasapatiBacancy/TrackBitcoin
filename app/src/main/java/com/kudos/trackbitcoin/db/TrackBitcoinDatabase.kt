package com.kudos.trackbitcoin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kudos.trackbitcoin.db.dao.TrackBitcoinDao
import com.kudos.trackbitcoin.db.entity.BitcoinRateDB

@Database(entities = [BitcoinRateDB::class], version = 1, exportSchema = false)
abstract class TrackBitcoinDatabase : RoomDatabase() {
    abstract fun trackBitcoinDao(): TrackBitcoinDao
}