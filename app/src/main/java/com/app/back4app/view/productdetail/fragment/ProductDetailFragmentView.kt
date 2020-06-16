package com.app.back4app.view.productdetail.fragment

import com.app.back4app.model.product.Product

interface ProductDetailFragmentView {
    /**
     * display product to page detail
     */
    fun onLoadProductData(product: Product)

    /**
     * add product to cart to order
     */
    fun onAddToCart(product: Product)
}