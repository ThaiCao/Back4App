package com.app.back4app.view.product.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.back4app.R
import com.app.back4app.model.product.Product
import com.app.back4app.view.base.viewholder.BaseViewHolder

class ProductAdapter (private var products: List<Product>, private  val listener: ProductItemListener) : RecyclerView.Adapter<BaseViewHolder>(){

    private val onItemClickListener: ProductItemListener = object : ProductItemListener {
        override fun onItemSelected(data: Any, position: Int) {
            listener.onItemSelected(data, position)
        }
    }

    fun setProducts(data: List<Product>){
        this.products = data
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(products[position], position, onItemClickListener)
    }
}