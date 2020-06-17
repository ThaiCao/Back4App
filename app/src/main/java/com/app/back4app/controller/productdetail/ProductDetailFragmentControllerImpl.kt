package com.app.back4app.controller.productdetail

import com.app.back4app.model.product.Product
import com.app.back4app.view.productdetail.fragment.ProductDetailFragment

class ProductDetailFragmentControllerImpl(val view: ProductDetailFragment): ProductDetailFragmentController {

    /**
     * load product data to display on page detail
     */
    override fun onLoadData(product: Product) {
        view.onLoadProductData(product)
    }

    /**
     * add product to cart
     */
    override fun onAddToCart(product: Product) {
        // save to db temp
        view.onAddToCart(product)
    }
}