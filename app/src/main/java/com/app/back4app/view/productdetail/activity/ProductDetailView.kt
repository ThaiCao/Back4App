package com.app.back4app.view.productdetail.activity

import com.app.back4app.model.product.Product

interface ProductDetailView {
    /**
     * open product detail fragment page with data
     */
    fun openProductDetailFragment(product: Product)
}