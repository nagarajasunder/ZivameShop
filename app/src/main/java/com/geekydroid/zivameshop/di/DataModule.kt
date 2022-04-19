package com.geekydroid.zivameshop.di

import android.app.Application
import com.geekydroid.zivameshop.ZivameShop
import com.geekydroid.zivameshop.data.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesApplication(app: Application) = app as ZivameShop

    @Provides
    @Singleton
    @Named("BASE_URL")
    fun providesBaseURL() = "https://my-json-server.typicode.com/"

    @Provides
    @Singleton
    fun providesRemoteDataSource(@Named("BASE_URL") url: String): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }




}