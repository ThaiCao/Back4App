package com.app.back4app.view.product.fragment

import com.app.back4app.model.product.Product
import com.parse.ParseException

interface ProductFragmentView {
    fun onGetProductsCompleted(data: List<Product>, ex: ParseException?)
}