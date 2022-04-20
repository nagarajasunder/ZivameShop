package com.geekydroid.zivameshop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geekydroid.zivameshop.dao.OrdersDao
import com.geekydroid.zivameshop.entity.Orders

@Database(entities = [Orders::class], version = 1, exportSchema = false)
abstract class LocalDataSource : RoomDatabase() {

    abstract fun getOrdersDao(): OrdersDao

}