package com.app.back4app.view.order.fragment

import com.app.back4app.model.product.Product

interface OrderFragmentView {
    /**
     * get products from Realm - order column
     */
    fun onGetProductsCompleted(data: List<Product>)

    /**
     * display price
     */
    fun onDisplayPrice(price: Int)
}