package com.app.back4app.view.order.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.back4app.R
import com.app.back4app.model.product.Product
import com.app.back4app.view.base.viewholder.BaseViewHolder

class OrderAdapter (private var products: List<Product>, private  val listener: OrderItemListener) : RecyclerView.Adapter<BaseViewHolder>(){

    private val onItemClickListener: OrderItemListener = object : OrderItemListener {
        override fun onItemSelected(data: Any, position: Int) {
            listener.onItemSelected(data, position)
        }
    }

    fun setProducts(data: List<Product>){
        this.products = data
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(products[position], position, onItemClickListener)
    }
}