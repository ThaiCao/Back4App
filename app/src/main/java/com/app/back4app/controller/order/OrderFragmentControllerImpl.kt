package com.app.back4app.controller.order

import com.app.back4app.model.order.Order
import com.app.back4app.model.order.OrderProduct
import com.app.back4app.model.product.Product
import com.app.back4app.realm.RealmDatabase
import com.app.back4app.view.order.fragment.OrderFragment
import com.google.gson.Gson
import com.parse.ParseObject
import com.parse.SaveCallback
import org.json.JSONObject


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

    override fun saveOrder(products: List<Product>, amount: Int) {
//        val order = ParseObject("Order")
        val order = Order()
        order.amount = amount
        order.products = ArrayList()
//        val productItems = ArrayList<OrderProduct>()
        for(productItem : Product in products){
            val title = productItem.title
//            val price: Int? = productItem.price
//            val picture = productItem.picture?.url
//            val content = productItem.content
//            val options = productItem.options
//            val orderProduct = OrderProduct(title, price!!, picture, content!!,
//                options as ArrayList<String>
//            )
//            productItems.add(title)
            if (title != null) {
                (order.products as ArrayList).add(title)
            }
        }
//        val parseProductArray: MutableList<List<*>> =
//            ArrayList()
//        for (product: Product in products) {
//            val innerList: MutableList<JSONObject> = ArrayList()
//            val jsonObject = JSONObject(Gson().toJson(product))
//            innerList.add(jsonObject)
//            parseProductArray.add(innerList)
//        }
//        order.put("amount", amount)
//        order.put("products", productItems)
//        order.put("products", products)
        order.saveInBackground{
            if(it == null){
                view.saveOrderSuccess()
            }else{
                view.saveOrderFail(it)
            }

        }
    }

    override fun clearOrderData() {
        RealmDatabase.newInstance().clearOrderData()
    }
}