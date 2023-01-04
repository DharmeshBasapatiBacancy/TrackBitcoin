package com.kudos.trackbitcoin.network.models

data class TrackBitcoinResponseApi(
    val bpi: Bpi,
    val chartName: String,
    val disclaimer: String,
    val time: Time
)