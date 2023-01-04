package com.kudos.trackbitcoin.network.service

import com.kudos.trackbitcoin.network.models.TrackBitcoinResponseApi
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface TrackBitcoinService {

    @GET("https://api.coindesk.com/v1/bpi/currentprice.json")
    suspend fun getBitcoinData(): TrackBitcoinResponseApi

}