package com.app.back4app.view.product.fragment

import com.app.back4app.view.base.listener.RecyclerItemListener

interface ProductItemListener : RecyclerItemListener {
    fun onItemSelected(data: Any, position: Int)
}