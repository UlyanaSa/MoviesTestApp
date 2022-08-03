package com.osvin.moviestestapp.di

import android.app.Application
import com.osvin.moviestestapp.data.network.MovieAPI
import com.osvin.moviestestapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun providesRetrofit(): MovieAPI = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieAPI::class.java)
}