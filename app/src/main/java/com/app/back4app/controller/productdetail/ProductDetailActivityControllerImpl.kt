package com.app.back4app.controller.productdetail

import com.app.back4app.model.product.Product
import com.app.back4app.view.productdetail.activity.ProductDetailActivity

class ProductDetailActivityControllerImpl(val view: ProductDetailActivity) :ProductDetailActivityController{
    override fun openProductDetailFragment(product: Product) {
        view.openProductDetailFragment(product)
    }
}