package com.app.back4app.realm

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

    fun onSaveProductOrder(product: Product, imageByte: ByteArray?) : Boolean{
        val realm: Realm = Realm.getDefaultInstance()
        try {
            val productOrder = ProductOrder()
            productOrder.title = product.title
            productOrder.content = product.content
            if(product.picture !=null && product.picture!!.url !=null){
                productOrder.picture = product.picture!!.url
            }
            if(imageByte !=null){
                productOrder.pictureBitmap = imageByte
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
            realm.close()
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
                    val product = Product()
                    product.title = product_.title
                    product.content = product_.content
//                    if(product_.picture !=null && product_.picture!!.isNotEmpty()){
//                        val bitmap = ImageUtils.newInstance().convertImageUrlToBitmap(product_.picture!!)
//                        if(bitmap !=null){
//                            val file = ParseFile(ImageUtils.newInstance().encodeToByteArray(bitmap))
////                            val file = ParseFile(ImageUtils.newInstance().encodeToByteArray(bitmap))
//                            product.picture = file
//                        }
//                    }
                    if(product_.pictureBitmap !=null ){
                        val file = ParseFile(product_.pictureBitmap)
                        product.picture = file
                    }
                    product.price = product_.price
                    if(product_.options !=null ){
                        product.options = ArrayList()
                        for(option_ : String in product_.options!!){
                            (product.options!! as ArrayList<String>).add(option_)
                        }
                    }
//                    product.options = product_.options
                    products.add(product)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            realm.close()
        }

        return products
    }

    fun clearOrderData(){
        val realm: Realm = Realm.getDefaultInstance()
        try{
            realm.executeTransaction { realm ->
                val result: RealmResults<ProductOrder> =
                    realm.where<ProductOrder>(ProductOrder::class.java)
                        .findAll()
                result.deleteAllFromRealm()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            realm.close()
        }

    }
}