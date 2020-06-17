package com.app.back4app.view.order.activity

import com.app.back4app.R
import com.app.back4app.controller.order.OrderActivityController
import com.app.back4app.controller.order.OrderActivityControllerImpl
import com.app.back4app.view.base.activity.BaseActivity
import com.app.back4app.view.order.fragment.OrderFragment

class OrderActivity: BaseActivity(), OrderView {

    lateinit var orderController : OrderActivityController

    override val layoutId: Int
        get() = R.layout.activity_cart


    override fun initData() {
        orderController = OrderActivityControllerImpl(this)
    }

    override fun initView() {
        setTitle(getString(R.string.order))
        openOrderFragment()
    }

    /**
     * open product page
     */
    override fun openOrderFragment() {
        replaceFragment(OrderFragment.newInstance(), OrderFragment.TAG)
    }

    /**
     * handle back press
     */
    override fun onBackPressed() {
        finish()
    }
}