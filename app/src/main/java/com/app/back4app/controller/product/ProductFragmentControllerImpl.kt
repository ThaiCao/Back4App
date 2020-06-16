package com.app.back4app.controller.product

import com.app.back4app.model.product.Product
import com.app.back4app.view.product.fragment.ProductFragment
import com.parse.ParseQuery

class ProductFragmentControllerImpl(val view: ProductFragment) : ProductFragmentController{

    override fun getProducts(){
        val query = ParseQuery.getQuery(Product::class.java)
        query.findInBackground { objects, e ->
            view.onGetProductsCompleted(objects, e)
        }
    }
}