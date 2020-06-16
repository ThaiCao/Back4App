package com.app.back4app.view.productdetail.activity

import com.app.back4app.R
import com.app.back4app.model.product.Product
import com.app.back4app.view.base.activity.BaseActivity
import com.app.back4app.view.productdetail.fragment.ProductDetailFragment

class ProductDetailActivity : BaseActivity(), ProductDetailView {

    companion object{
        val PRODUCT_DATA : String ="PRODUCT_DATA"
    }

    var product: Product? = null

    override val layoutId: Int
        get() = R.layout.activity_product_detail

    override fun initData() {
        if(intent.hasExtra(PRODUCT_DATA)){
            product = intent.getParcelableExtra(PRODUCT_DATA)
        }
    }

    override fun initView() {
        product?.let { openProductDetailFragment(it) }
    }

    /**
     * open product detail page
     */
    private fun openProductDetailFragment(product: Product) {
        replaceFragment(ProductDetailFragment.newInstance(product), ProductDetailFragment.TAG)
    }

    override fun onBackPressed() {
        finish()
    }
}