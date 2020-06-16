package com.app.back4app.view.product.acitivity

import com.app.back4app.R
import com.app.back4app.controller.product.ProductActivityController
import com.app.back4app.controller.product.ProductActivityControllerImpl
import com.app.back4app.view.base.activity.BaseActivity
import com.app.back4app.view.product.fragment.ProductFragment

class ProductActivity: BaseActivity(), ProductView {
    override val layoutId: Int
        get() = R.layout.activity_product

    lateinit var productController : ProductActivityController

    override fun initData() {
        productController = ProductActivityControllerImpl(this)
    }

    override fun initView() {
        setTitle(getString(R.string.products))
        openProductFragment()
    }

    /**
     * open product page
     */
    override fun openProductFragment() {
        replaceFragment(ProductFragment.newInstance(), ProductFragment.TAG)
    }

    /**
     * handle back press
     */
    override fun onBackPressed() {
        finish()
    }
}