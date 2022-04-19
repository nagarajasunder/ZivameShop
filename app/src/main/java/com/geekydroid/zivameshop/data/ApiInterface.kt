package com.geekydroid.zivameshop.data

import com.geekydroid.zivameshop.entity.Products
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("nancymadan/assignment/db")
    suspend fun getProductList(): Response<Products>

}