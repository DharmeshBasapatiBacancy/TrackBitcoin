package com.kudos.trackbitcoin.di

import com.kudos.trackbitcoin.db.dao.TrackBitcoinDao
import com.kudos.trackbitcoin.network.service.TrackBitcoinService
import com.kudos.trackbitcoin.repository.TrackBitcoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideQuotesRepository(
        trackBitcoinService: TrackBitcoinService,
        trackBitcoinDao: TrackBitcoinDao
    ): TrackBitcoinRepository {
        return TrackBitcoinRepository(trackBitcoinService, trackBitcoinDao)
    }

}