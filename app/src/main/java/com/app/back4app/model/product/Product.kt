package com.app.back4app.model.product

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ktx.putOrIgnore

@ParseClassName(Product.NAME)
class Product : ParseObject() {

    companion object {
        const val NAME = "Product"

        const val KEY_TITLE = "title"
        const val KEY_PRICE= "price"
        const val KEY_PICTURE= "picture"
        const val KEY_CONTENT= "content"
        const val KEY_OPTIONS= "options"
    }

    var title: String?
        get() = getString(KEY_TITLE)
        set(value) = putOrIgnore(KEY_TITLE, value)

    var price: Int?
        get() = getInt(KEY_PRICE)
        set(value) = putOrIgnore(KEY_PRICE, value)

    var picture: ParseFile?
        get() = getParseFile(KEY_PICTURE) as ParseFile?
        set(value) = putOrIgnore(KEY_PICTURE, value)


    var content: String?
        get() = getString(KEY_CONTENT)
        set(value) = putOrIgnore(KEY_CONTENT, value)

    var options: List<String>?
        get() = getList<String>(KEY_OPTIONS)
        set(value) = putOrIgnore(KEY_OPTIONS, value)
}