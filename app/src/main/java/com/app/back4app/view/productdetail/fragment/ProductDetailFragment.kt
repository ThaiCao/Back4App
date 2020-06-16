package com.app.back4app.view.productdetail.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.app.back4app.R
import com.app.back4app.controller.productdetail.ProductDetailFragmentController
import com.app.back4app.controller.productdetail.ProductDetailFragmentControllerImpl
import com.app.back4app.model.product.Product
import com.app.back4app.view.base.fragment.BaseFragment
import com.app.back4app.view.base.listener.RecyclerItemListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_product_detail.*

class ProductDetailFragment(val product: Product) : BaseFragment(), ProductDetailFragmentView,
    RecyclerItemListener {

    companion object {
        val TAG: String = "ProductDetailFragment"

        fun newInstance(product: Product) = ProductDetailFragment(product)
    }

    lateinit var productDetailController : ProductDetailFragmentController
    var adapter: OptionAdapter? = null

    override val layoutId: Int
        get() = R.layout.fragment_product_detail

    override fun initData() {
        productDetailController = ProductDetailFragmentControllerImpl(this)
        productDetailController.onLoadData(product)
    }

    override fun initListeners() {
        btn_add_to_cart.setOnClickListener {
            productDetailController.onAddToCart(product)
        }
    }

    override fun initView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_option.layoutManager = layoutManager
        rv_option.setHasFixedSize(true)
    }

    override fun onLoadProductData(product: Product) {
        tv_content.text = product.content
        tv_product_title.text = product.title
        Glide.with(context)
            .load(product.picture?.url)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .centerCrop()
                    .dontTransform()
            )
            .into(iv_product)

        adapter = product.options?.let { OptionAdapter(it, this) }
        rv_option.adapter = adapter
    }

    override fun onAddToCart(product: Product) {

    }
}