package com.app.back4app.view.order.fragment

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.back4app.R
import com.app.back4app.controller.order.OrderFragmentController
import com.app.back4app.controller.order.OrderFragmentControllerImpl
import com.app.back4app.model.product.Product
import com.app.back4app.view.base.fragment.BaseFragment
import com.app.back4app.view.order.activity.OrderActivity
import com.parse.ParseException
import kotlinx.android.synthetic.main.fragment_cart.*

class OrderFragment: BaseFragment(), OrderFragmentView, OrderItemListener {

    companion object {
        val TAG: String = "OrderFragment"

        fun newInstance() = OrderFragment()
    }

    lateinit var adapter: OrderAdapter
    var products: List<Product> = ArrayList()
    var amount: Int = 0

    lateinit var orderController : OrderFragmentController

    override val layoutId: Int
        get() = R.layout.fragment_cart

    override fun initData() {
        orderController = OrderFragmentControllerImpl(this)
        orderController.getProducts()
    }

    override fun initListeners() {
        btn_Order.setOnClickListener {
            orderController.saveOrder(products,amount )

        }
    }

    override fun initView() {
        val layoutManager = LinearLayoutManager (context)
        rv_product.layoutManager = layoutManager
        rv_product.setHasFixedSize(true)
        adapter = OrderAdapter(products, this)
        rv_product.adapter = adapter
    }

    override fun onGetProductsCompleted(data: List<Product>) {
        if(data.isNotEmpty()){
            products = data
            adapter.setProducts(data)
        }
    }

    override fun onDisplayPrice(price: Int) {
        amount = price
        tv_price.text = price.toString()
    }

    override fun saveOrderFail(ex: ParseException) {
        Toast.makeText(context, getString(R.string.add_order_fail), Toast.LENGTH_SHORT).show()
    }

    override fun saveOrderSuccess() {
        orderController.clearOrderData()
        Toast.makeText(context, getString(R.string.add_order_success), Toast.LENGTH_SHORT).show()
        if(activity is OrderActivity){
            (activity as OrderActivity).finish()
        }
    }

    override fun onItemSelected(data: Any, position: Int) {

    }
}