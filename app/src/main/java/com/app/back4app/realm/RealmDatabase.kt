package com.app.back4app.realm

import android.content.Context
import com.app.back4app.model.product.Product
import com.app.back4app.realm.model.ProductOrder
import com.parse.ParseFile
import io.realm.Realm
import io.realm.RealmResults

class RealmDatabase {

    companion object {
        val TAG: String = "RealmDatabase"
        fun newInstance() =
            RealmDatabase()
    }

    fun onSaveProductOrder(product: Product) : Boolean{
        val realm: Realm = Realm.getDefaultInstance()
        try {
            val productOrder = ProductOrder()
            productOrder.title = product.title
            productOrder.content = product.content
            if(product.picture !=null && product.picture!!.url !=null){
                productOrder.picture = product.picture!!.url
            }
            productOrder.price = product.price
            if(product.options !=null ){
                for(option_ : String in product.options!!){
                    productOrder.options!!.add(option_)
                }

            }

            realm.beginTransaction()
            realm.copyToRealm(productOrder)
            realm.commitTransaction()
            return true
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            realm.close();
        }
        return false
    }

    fun getProductsOrder(): List<Product> {
        var products: ArrayList<Product> = ArrayList()
        val realm: Realm = Realm.getDefaultInstance()
        try {
            val results: RealmResults<ProductOrder> =
                realm.where(ProductOrder::class.java).findAll()
            if (results != null && results.isNotEmpty()) {
                for (product_: ProductOrder in results) {
                    val product: Product = Product()
                    product.title = product_.title
                    product.content = product_.content
                    if(product.picture !=null && product_.picture!!.isNotEmpty()){
                         val file = ParseFile(product_.picture!!.toByteArray(Charsets.UTF_8))
//                         file.save()
                        product.picture = file
                    }

                    product.price = product_.price
                    if(product_.options !=null ){
                        product.options = ArrayList()
                        for(option_ : String in product_.options!!){
                            (product.options!! as ArrayList<String>).add(option_)
                        }
                    }
                    product.options = product_.options
                    products.add(product)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            realm.close();
        }

        return products
    }
}