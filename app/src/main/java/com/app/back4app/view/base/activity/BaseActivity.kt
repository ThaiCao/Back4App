package com.app.back4app.view.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.back4app.R
import com.app.back4app.extension.addFragment
import com.app.back4app.extension.replaceFragment
import com.app.back4app.view.base.listener.BaseView
import java.util.ArrayList

abstract class BaseActivity : AppCompatActivity(), BaseView {
    private val activitiesStack: ArrayList<BaseActivity> = ArrayList()
    abstract val layoutId: Int

    protected abstract fun initData()
    protected abstract fun initView()

    override fun vHideKeyboard() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitiesStack.add(this)
        setContentView(layoutId)
        initData()
        initView()
    }

    override fun setTitle(titleKey: String) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title  = titleKey
        }
    }

    fun replaceFragment(fragment: Fragment, tag: String){ supportFragmentManager.replaceFragment(R.id.content, fragment, tag) }

    fun addFragment(fragment: Fragment, tag: String){ supportFragmentManager.addFragment(R.id.content, fragment, tag) }

    override fun onDestroy() {
        super.onDestroy()
        activitiesStack.remove(this)
    }

    open fun getActivitiesStack(): List<BaseActivity?>? {
        return activitiesStack
    }

    open fun getVisibleActivity(): BaseActivity? {
        return if (activitiesStack.size > 0) {
            activitiesStack[activitiesStack.size - 1]
        } else null
    }
}