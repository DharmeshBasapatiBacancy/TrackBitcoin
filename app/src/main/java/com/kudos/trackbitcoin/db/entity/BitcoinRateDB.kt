package com.kudos.trackbitcoin.db.entity

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bitcoinRates")
data class BitcoinRateDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val usdPrice: String,
    val ukPrice: String,
    val euroPrice: String,
)
