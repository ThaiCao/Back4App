package com.app.back4app.view.product.fragment

import android.util.Log
import android.view.View
import com.app.back4app.model.product.Product
import com.app.back4app.view.base.listener.RecyclerItemListener
import com.app.back4app.view.base.viewholder.BaseViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_product.view.*

class ProductItemViewHolder(override val containerView: View) : BaseViewHolder(containerView) {
    override fun bind(data: Any, position: Int, recyclerItemListener: RecyclerItemListener) {
        if(data is Product){
            itemView.tv_product_title.text = data.title
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
                if (recyclerItemListener is ProductItemListener) {
                    recyclerItemListener.onItemSelected(data, position)
                }
            }
        }
    }
}