package com.app.back4app.view.productdetail.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.back4app.R
import com.app.back4app.view.base.listener.RecyclerItemListener
import com.app.back4app.view.base.viewholder.BaseViewHolder

class OptionAdapter(private var option: List<String>, private  val listener: RecyclerItemListener) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_option, parent, false)
        return OptionItemViewHolder(view)
    }

    override fun getItemCount(): Int {
       return option.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(option[position], position, listener)
    }
}