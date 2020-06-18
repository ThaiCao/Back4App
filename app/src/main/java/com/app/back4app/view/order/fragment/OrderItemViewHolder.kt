package com.app.back4app.view.order.fragment

import android.view.View
import com.app.back4app.R
import com.app.back4app.model.product.Product
import com.app.back4app.view.base.listener.RecyclerItemListener
import com.app.back4app.view.base.viewholder.BaseViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_order.view.*
import kotlinx.android.synthetic.main.item_product.view.iv_product
import kotlinx.android.synthetic.main.item_product.view.tv_product_title

class OrderItemViewHolder(override val containerView: View) : BaseViewHolder(containerView) {
    override fun bind(data: Any, position: Int, recyclerItemListener: RecyclerItemListener) {
        if(data is Product){
            itemView.tv_product_title.text = data.title
            itemView.tv_product_price.text = containerView.resources.getString(R.string.price) +" $"+  data.price.toString()
            Glide.with(itemView.context)
                .load(data.picture?.url)
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .dontTransform()
                )
                .into(itemView.iv_product)
            itemView.setOnClickListener {
                if (recyclerItemListener is OrderItemListener) {
                    recyclerItemListener.onItemSelected(data, position)
                }
            }
        }
    }
}