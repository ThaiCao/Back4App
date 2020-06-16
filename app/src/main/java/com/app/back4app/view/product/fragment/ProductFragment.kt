package com.app.back4app.view.product.fragment

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.back4app.R
import com.app.back4app.controller.product.ProductFragmentController
import com.app.back4app.controller.product.ProductFragmentControllerImpl
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
        productController = ProductFragmentControllerImpl(this)
        productController.getProducts()
    }

    override fun initListeners() {
    }

    override fun initView() {
        val layoutManager = LinearLayoutManager (context)
        rv_product.layoutManager = layoutManager
        rv_product.setHasFixedSize(true)
        adapter = ProductAdapter(products, this)
        rv_product.adapter = adapter
    }

    override fun onGetProductsCompleted(data: List<Product>, ex: ParseException?) {
        if (ex == null) {
            if(data.isNotEmpty()){
                adapter.setProducts(data)
            }
        } else {
            Log.e("TEST_DATA", "Error: ${ex.message}")
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