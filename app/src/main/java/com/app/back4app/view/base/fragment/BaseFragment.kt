package com.app.back4app.view.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.back4app.R
import com.app.back4app.view.base.activity.BaseActivity
import com.app.back4app.view.base.listener.BaseView

abstract class BaseFragment : Fragment(), BaseView {

    abstract val layoutId: Int

    abstract fun initData()

    abstract fun initListeners()

    abstract fun initView()

    val container by lazy { view?.findViewById<View>(R.id.container) }

    override fun setTitle(titleKey: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).setTitle(titleKey)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initListeners()
        initDefaultClickHideKeyboard()
    }

    private fun initDefaultClickHideKeyboard() {
        container?.setOnClickListener { vHideKeyboard() }
    }

    override fun vHideKeyboard() {
        (activity as BaseActivity).vHideKeyboard()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }
}