package com.app.back4app.controller.order

import com.app.back4app.model.product.Product

interface OrderFragmentController {
    fun getProducts()

    fun saveOrder(products: List<Product>, amount: Int)

    fun clearOrderData()
}