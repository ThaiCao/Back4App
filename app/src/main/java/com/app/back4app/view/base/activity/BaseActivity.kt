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

    // init data
    protected abstract fun initData()
    // init view
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

    /**
     * set title for app
     */
    override fun setTitle(titleKey: String?) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title  = titleKey
        }
    }

    /**
     * replace fragment with tag
     */
    fun replaceFragment(fragment: Fragment, tag: String){ supportFragmentManager.replaceFragment(R.id.content, fragment, tag) }

    /**
     * add fragment with tag
     */
    fun addFragment(fragment: Fragment, tag: String){ supportFragmentManager.addFragment(R.id.content, fragment, tag) }

    override fun onDestroy() {
        super.onDestroy()
        activitiesStack.remove(this)
    }

    /**
     * get list stack activities
     */
    open fun getActivitiesStack(): List<BaseActivity?>? {
        return activitiesStack
    }

    /**
     * get activity that is visible
     */
    open fun getVisibleActivity(): BaseActivity? {
        return if (activitiesStack.size > 0) {
            activitiesStack[activitiesStack.size - 1]
        } else null
    }
}