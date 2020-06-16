package com.app.back4app.controller.productdetail

import com.app.back4app.model.product.Product

interface ProductDetailActivityController{
    fun openProductDetailFragment(product: Product)
}