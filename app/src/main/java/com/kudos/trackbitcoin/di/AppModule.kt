package com.kudos.trackbitcoin.di

import android.content.Context
import androidx.work.WorkManager
import com.kudos.trackbitcoin.db.dao.TrackBitcoinDao
import com.kudos.trackbitcoin.network.service.TrackBitcoinService
import com.kudos.trackbitcoin.repository.TrackBitcoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

}