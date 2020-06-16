package com.app.back4app.view.productdetail.fragment

import android.view.View
import com.app.back4app.view.base.listener.RecyclerItemListener
import com.app.back4app.view.base.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_option.view.*

class OptionItemViewHolder (override val containerView: View) : BaseViewHolder(containerView) {

    override fun bind(data: Any, position: Int, recyclerItemListener: RecyclerItemListener) {
        if(data is String){
            itemView.tv_option.text = data
        }
    }
}