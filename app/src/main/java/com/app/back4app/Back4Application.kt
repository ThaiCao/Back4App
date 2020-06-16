package com.app.back4app

import android.app.Application
import android.content.Context
import com.app.back4app.model.product.Product
import com.parse.Parse
import com.parse.ParseObject

class Back4Application: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        // Register class
        ParseObject.registerSubclass(Product::class.java)
//        ParseObject.registerSubclass(ProductPicture::class.java)
//        ParseObject.registerSubclass(PictureState::class.java)

        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("QTXOYgXJ7PoAVu6951M2FAVJQMCegyG0cE2asnSc") // if desired
                .clientKey("BPC21xjhn48saiI7SKDuvyttUfmmOfHk3wpwJZJX")
                .server("https://thaitest.back4app.io/")
                .build()
        )
    }
}