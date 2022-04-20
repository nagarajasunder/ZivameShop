package com.geekydroid.zivameshop.repository

import com.geekydroid.zivameshop.dao.OrdersDao
import javax.inject.Inject


class CartRepository @Inject constructor(val ordersDao: OrdersDao) {

    fun getPendingOrders() = ordersDao.getPendingOrders()

}