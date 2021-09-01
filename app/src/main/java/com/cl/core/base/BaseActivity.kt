package com.cl.core.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        initToolbar(getCurrentToolBar())

        handleBackButton()
    }

    /**
     * Method that return fragment placeholder id
     *
     * @return The fragment placeholder id
     */
    protected abstract fun getFragmentPlaceHolder(): Int

    protected abstract fun getCurrentToolBar(): Toolbar

    /**
     * navigate to next fragment
     *
     * @param nextFragment
     * @param previousFragmentTag
     * @param addToBackStack
     * @param fragmentTransition
     * @param currentFragmentTag
     **/
    open fun navigateToFragment(
        nextFragment: BaseFragment<*>, previousFragmentTag: String?,
        addToBackStack: Boolean, fragmentTransition: Int, currentFragmentTag: String?
    ) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setTransition(fragmentTransition)
        fragmentTransaction.replace(getFragmentPlaceHolder(), nextFragment, currentFragmentTag)
        if (addToBackStack) fragmentTransaction.addToBackStack(previousFragmentTag)
        fragmentTransaction.commit()
    }

    private fun handleBackButton() {
        val currentFragmentShownTag = getTopLevelFragmentTag()
        val currentFragment =
            supportFragmentManager.findFragmentByTag(currentFragmentShownTag) as BaseFragment<*>

        if (currentFragment.isTopLevelFragment()) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        } else {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun getTopLevelFragmentTag(): String? {
        return supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name
    }

    private fun initToolbar(toolBar: Toolbar) {
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        toolBar.setNavigationOnClickListener { view -> onBackPressed() }
    }

    fun setTitle(string: String) {
        supportActionBar?.title = string
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}