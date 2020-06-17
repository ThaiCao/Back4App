package com.app.back4app.controller.order

import com.app.back4app.model.product.Product
import com.app.back4app.realm.RealmDatabase
import com.app.back4app.view.order.fragment.OrderFragment

class OrderFragmentControllerImpl(val view: OrderFragment) : OrderFragmentController {
    override fun getProducts() {
        val products = RealmDatabase.newInstance().getProductsOrder()
        if(products !=null && products.isNotEmpty()){
            view.onGetProductsCompleted(products)
            var price: Int = 0
            for(product: Product in products){
                price += product.price!!
            }
            view.onDisplayPrice(price)
        }
    }
}