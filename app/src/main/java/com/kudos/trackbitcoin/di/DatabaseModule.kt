package com.kudos.trackbitcoin.di

import android.content.Context
import androidx.room.Room
import com.kudos.trackbitcoin.db.TrackBitcoinDatabase
import com.kudos.trackbitcoin.db.dao.TrackBitcoinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object DatabaseModule {

    @Provides
    fun provideTrackBitcoinDao(trackBitcoinDatabase: TrackBitcoinDatabase): TrackBitcoinDao {
        return trackBitcoinDatabase.trackBitcoinDao()
    }

    @Provides
    @Singleton
    fun provideTrackBitcoinDatabase(
        @ApplicationContext context: Context,
    ): TrackBitcoinDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TrackBitcoinDatabase::class.java,
            "TrackBitcoinDatabase"
        ).build()
    }

}