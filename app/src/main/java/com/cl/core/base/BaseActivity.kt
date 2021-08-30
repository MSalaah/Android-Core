package com.cl.core.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    /**
     * Method that return fragment placeholder id
     *
     * @return The fragment placeholder id
     */
    protected abstract fun getFragmentPlaceHolder(): Int

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun navigateToFragment(nextFragment: BaseFragment<*>, tag: String, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(getFragmentPlaceHolder(), nextFragment)
        if (addToBackStack)
            fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
    }

    fun initToolbar(toolBar: Toolbar) {
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        toolBar.setNavigationOnClickListener { view -> onBackPressed() }
    }

    fun updateTitle(string: String) {
        supportActionBar!!.title = string
    }

    fun setIsHomeFragment(isHome: Boolean) {
        if (isHome)
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        else
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}