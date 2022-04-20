package com.geekydroid.zivameshop.utils

import com.geekydroid.zivameshop.entity.Product

interface AddToCartListener {

    fun addToCart(product: Product)

}