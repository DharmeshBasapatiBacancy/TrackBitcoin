package com.kudos.trackbitcoin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kudos.trackbitcoin.db.entity.BitcoinRateDB
import com.kudos.trackbitcoin.repository.TrackBitcoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackBitcoinViewModel @Inject constructor(private val trackBitcoinRepository: TrackBitcoinRepository) :
    ViewModel() {

    val latestBitcoinRates: LiveData<List<BitcoinRateDB>> =
        trackBitcoinRepository.getLatestBitcoinRates().asLiveData()

}