package com.app.back4app.view.order.fragment

import com.app.back4app.view.base.listener.RecyclerItemListener

interface OrderItemListener : RecyclerItemListener {
    fun onItemSelected(data: Any, position: Int)
}