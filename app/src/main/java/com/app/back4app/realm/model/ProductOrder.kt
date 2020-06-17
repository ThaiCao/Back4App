package com.app.back4app.realm.model

import io.realm.RealmList
import io.realm.RealmObject

open class ProductOrder : RealmObject() {

    var title: String? = null

    var price: Int? = null

    var picture: String? = null

    var content: String? = null

    var options: RealmList<String>?  = RealmList()
}