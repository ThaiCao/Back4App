package com.app.back4app.model.order

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ktx.putOrIgnore

@ParseClassName(Order.NAME)
class Order: ParseObject()  {
    companion object {
        const val NAME = "Order"

        const val KEY_AMOUNT = "amount"
        const val KEY_PRODUCTS= "products"
    }

    var amount: Int?
        get() = getInt(KEY_AMOUNT)
        set(value) = putOrIgnore(KEY_AMOUNT, value)

    var products: List<String>?
        get() = getList(KEY_PRODUCTS)
        set(value) = putOrIgnore(KEY_PRODUCTS, value)
}