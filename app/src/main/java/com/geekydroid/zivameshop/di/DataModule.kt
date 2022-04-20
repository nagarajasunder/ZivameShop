package com.geekydroid.zivameshop.di

import android.app.Application
import androidx.room.Room
import com.geekydroid.zivameshop.ZivameShop
import com.geekydroid.zivameshop.dao.OrdersDao
import com.geekydroid.zivameshop.data.ApiInterface
import com.geekydroid.zivameshop.data.LocalDataSource
import com.geekydroid.zivameshop.repository.CartRepository
import com.geekydroid.zivameshop.repository.HomeRepository
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

    @Provides
    @Singleton
    fun providesLocalDataSource(app: Application): LocalDataSource {
        return Room.databaseBuilder(
            app.applicationContext,
            LocalDataSource::class.java,
            "zivame_shop.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesOrdersDao(datasource: LocalDataSource): OrdersDao = datasource.getOrdersDao()

    @Provides
    @Singleton
    fun providesCartRepository(ordersDao: OrdersDao): CartRepository = CartRepository(ordersDao)

    @Provides
    @Singleton
    fun providesHomeRepository(ordersDao: OrdersDao): HomeRepository = HomeRepository(ordersDao)

}