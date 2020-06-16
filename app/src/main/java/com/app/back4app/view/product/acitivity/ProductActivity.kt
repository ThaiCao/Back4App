package com.app.back4app.view.product.acitivity

import com.app.back4app.R
import com.app.back4app.view.base.activity.BaseActivity
import com.app.back4app.view.product.fragment.ProductFragment

class ProductActivity: BaseActivity(), ProductView {
    override val layoutId: Int
        get() = R.layout.activity_product

    override fun initData() {
    }

    override fun initView() {
        openProductFragment()
    }

    /**
     * open product page
     */
    private fun openProductFragment() {
        replaceFragment(ProductFragment.newInstance(), ProductFragment.TAG)
    }

    override fun onBackPressed() {
        finish()
    }
}