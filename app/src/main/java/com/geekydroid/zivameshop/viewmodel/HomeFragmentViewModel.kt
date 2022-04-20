package com.geekydroid.zivameshop.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekydroid.zivameshop.data.ApiInterface
import com.geekydroid.zivameshop.entity.Orders
import com.geekydroid.zivameshop.entity.Product
import com.geekydroid.zivameshop.entity.Products
import com.geekydroid.zivameshop.repository.HomeRepository
import com.geekydroid.zivameshop.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val apiInterface: ApiInterface,
    private val repository: HomeRepository
) :
    ViewModel() {

    private val products: MutableLiveData<Resource<Products>> = MutableLiveData()
    private val cartProductsIdentifier: MutableList<String> = mutableListOf()

    init {
        loadProducts()
        Log.d("HomeFragment", ": made network call")
    }

    private fun loadProducts() {
        viewModelScope.launch {
            products.postValue(Resource.Loading("Getting the products"))
            val response = apiInterface.getProductList()
            products.postValue(handleResponse(response))

        }
    }

    private fun handleResponse(response: Response<Products>): Resource<Products> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(data = it)
            }
        }
        return Resource.Error(error = response.message())
    }

    fun getProducts() = products

    fun insertOrder(orders: Product) {
        viewModelScope.launch {
            if (!cartProductsIdentifier.contains(orders.name)) {
                val order = Orders(0, orders, "IN_PROGRESS", orders.price.toDouble())
                repository.insertOrder(order)
                cartProductsIdentifier.add(orders.name)
            }
        }
    }


}