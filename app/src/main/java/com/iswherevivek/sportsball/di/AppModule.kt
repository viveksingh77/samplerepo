package com.iswherevivek.sportsball.di

import android.app.Application
import androidx.room.Room
import com.iswherevivek.sportsball.data.local.SportDao
import com.iswherevivek.sportsball.data.local.SportTypeConverter
import com.iswherevivek.sportsball.data.local.SportsDb
import com.iswherevivek.sportsball.data.remote.SportsApi
import com.iswherevivek.sportsball.data.repository.SportRepositoryImpl
import com.iswherevivek.sportsball.domain.repository.SportRepository
import com.iswherevivek.sportsball.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesSportsApi(): SportsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SportsApi::class.java)
    }

    @Singleton
    @Provides
    fun providesSportRepository(
        sportsApi: SportsApi,
        sportDao: SportDao
    ): SportRepository = SportRepositoryImpl(sportsApi , sportDao)

    @Provides
    @Singleton
    fun providesRoomDatabase(
        application: Application
    ): SportsDb {
        return Room.databaseBuilder(
            context = application,
            klass = SportsDb::class.java,
            name = Constants.SPORT_DATABASE_NAME
        ).addTypeConverter(SportTypeConverter()).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesSportDao(
        sportsDb: SportsDb
    ): SportDao = sportsDb.sportDao

}