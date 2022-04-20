package com.geekydroid.zivameshop.repository

import com.geekydroid.zivameshop.dao.OrdersDao
import com.geekydroid.zivameshop.entity.Orders
import javax.inject.Inject

class HomeRepository @Inject constructor(private val ordersDao: OrdersDao) {


    suspend fun insertOrder(order: Orders) {
        ordersDao.insertOrders(order)
    }


}