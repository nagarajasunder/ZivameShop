package com.geekydroid.zivameshop.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.geekydroid.zivameshop.entity.Orders

@Dao
interface OrdersDao {

    @Insert
    suspend fun insertOrders(orders: Orders)

    @Query("SELECT * FROM ORDERS WHERE orderStatus NOT LIKE '%' || 'COMPLETED' || '%'")
    fun getPendingOrders(): LiveData<List<Orders>>
}