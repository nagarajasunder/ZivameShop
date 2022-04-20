package com.geekydroid.zivameshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geekydroid.zivameshop.entity.Orders
import com.geekydroid.zivameshop.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    private val pendingOrders: LiveData<List<Orders>> by lazy {
        fetchOrders()
    }

    init {
        fetchOrders()
    }

    fun getOrders() = pendingOrders

    private fun fetchOrders() = repository.getPendingOrders()
}