package com.app.back4app

import android.app.Application
import android.content.Context
import com.app.back4app.model.product.Product
import com.parse.Parse
import com.parse.ParseObject
import io.realm.Realm
import io.realm.RealmConfiguration


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

        initRealm()
    }

    /**
     * init realm
     */
    private fun initRealm(){
        // Initialize Realm
        Realm.init(context);
    // The RealmConfiguration is created using the builder pattern.
        val config = RealmConfiguration.Builder()
            .name("back4app.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        // Use the config
        val realm: Realm = Realm.getInstance(config)
    }
}