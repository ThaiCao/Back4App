package com.app.back4app.view.base.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.back4app.view.base.listener.RecyclerItemListener
import kotlinx.android.extensions.LayoutContainer

open class BaseViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    open fun bind(data: Any, position: Int, recyclerItemListener: RecyclerItemListener){}
}