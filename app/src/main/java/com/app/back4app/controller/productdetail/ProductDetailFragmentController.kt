package com.app.back4app.controller.productdetail

import com.app.back4app.model.product.Product

interface ProductDetailFragmentController {
    /**
     * load product data to display on page detail
     */
    fun onLoadData(product: Product)

    /**
     * add product to cart
     */
    fun onAddToCart(product: Product)
}