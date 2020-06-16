package com.app.back4app.view.product.fragment

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.back4app.R
import com.app.back4app.controller.product.ProductFragmentController
import com.app.back4app.model.product.Product
import com.app.back4app.view.base.fragment.BaseFragment
import com.app.back4app.view.productdetail.activity.ProductDetailActivity
import com.google.gson.Gson
import com.parse.ParseException
import kotlinx.android.synthetic.main.fragment_product.*

class ProductFragment : BaseFragment(), ProductFragmentView, ProductItemListener {

    companion object {
        val TAG: String = "ProductFragment"

        fun newInstance() = ProductFragment()
    }

    lateinit var adapter: ProductAdapter
    var products: List<Product> = ArrayList()

    lateinit var productController : ProductFragmentController

    override val layoutId: Int
        get() = R.layout.fragment_product

    override fun initData() {
        adapter = ProductAdapter(products, this)

        productController = ProductFragmentController(this)
        productController.getProducts()
    }

    override fun initListeners() {
    }

    override fun initView() {
        val layoutManager = LinearLayoutManager (context)
        rv_product.layoutManager = layoutManager
        rv_product.setHasFixedSize(true)
        rv_product.adapter = adapter

    }

    override fun onGetProductsCompleted(data: List<Product>, ex: ParseException?) {
        if (ex == null) {
            if(data !=null && data.isNotEmpty()){
                for (product in data) {
                    Log.e("TEST_DATA", "product: ${Gson().toJson(product)}")
                    Log.e("TEST_DATA", "title: ${product.title}")
                    Log.e("TEST_DATA", "content: ${product.content}")
                    Log.e("TEST_DATA", "price: ${product.price}")
                    Log.e("TEST_DATA", "picture: ${product.picture?.url}")
                    Log.e("TEST_DATA", "options: ${product.options}")
                }
                adapter.setProducts(data)
            }
        } else {
            Log.e("TEST_DATA", "Error: " + ex!!.message)
        }
    }

    override fun onItemSelected(data: Any, position: Int) {
        Log.e("TEST_DATA", "onItemSelected: $position")
        if(data is Product){
            val it = Intent(activity, ProductDetailActivity::class.java)
            it.putExtra(ProductDetailActivity.PRODUCT_DATA, data)
            activity?.startActivity(it)
//            activity?.finish()
        }
    }
}