package com.app.back4app.view.productdetail.fragment

import android.content.Intent
import android.os.AsyncTask
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.back4app.R
import com.app.back4app.controller.productdetail.ProductDetailFragmentController
import com.app.back4app.controller.productdetail.ProductDetailFragmentControllerImpl
import com.app.back4app.model.product.Product
import com.app.back4app.realm.RealmDatabase
import com.app.back4app.utils.ImageUtils
import com.app.back4app.view.base.fragment.BaseFragment
import com.app.back4app.view.base.listener.RecyclerItemListener
import com.app.back4app.view.order.activity.OrderActivity
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
    var runable: Runnable? = null
    var thread: Thread? = null
    var imageByte: ByteArray? = null

    override val layoutId: Int
        get() = R.layout.fragment_product_detail

    override fun initData() {
        productDetailController = ProductDetailFragmentControllerImpl(this)
        productDetailController.onLoadData(product)
        getImageByte(product)
    }

    private fun getImageByte(product: Product){
        if(product.picture !=null && product.picture!!.url !=null){

            doAsync {
                // do work here ...
                val bitmap = ImageUtils.newInstance().convertImageUrlToBitmap(product.picture!!.url)
                imageByte = ImageUtils.newInstance().encodeToByteArray(bitmap)
                view_progress.post {
                    // update UI of myView ...
                    view_progress.visibility = View.GONE
                }
            }
        }

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
//        view_progress.visibility = View.VISIBLE
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
//        RealmDatabase.newInstance().onSaveProductOrder(product, imageByte)
////        Toast.makeText(context, getString(R.string.add_to_cart_success), Toast.LENGTH_SHORT)
//        val it = Intent(activity, OrderActivity::class.java)
//        activity?.startActivity(it)

        val isAddToCart = RealmDatabase.newInstance().onSaveProductOrder(product, imageByte)
        if(isAddToCart){
            Toast.makeText(context, getString(R.string.add_to_cart_success), Toast.LENGTH_SHORT).show()
            val it = Intent(activity, OrderActivity::class.java)
            activity?.startActivity(it)
        }else{
            Toast.makeText(context, getString(R.string.add_to_cart_fail), Toast.LENGTH_SHORT).show()
        }
    }

    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        init {
            execute()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }
}