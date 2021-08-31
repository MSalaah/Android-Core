package com.webkeyz.mvp.auth.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.cl.core.base.BaseFragment
import com.cl.core.feature.LoginViewModel


class LoginFragment : BaseFragment<LoginViewModel>() {

    companion object {
        val TAG = LoginFragment::class.java.simpleName
        fun newInstance(name: String): LoginFragment {
            val args = Bundle()
            args.putString("key", name)
            val loginFragment = LoginFragment()
            loginFragment.arguments = args
            return loginFragment
        }
    }

    override fun getContentLayout(): Int {
        TODO("Not yet implemented")
    }

    override fun injectViewModel(): Class<LoginViewModel> {
        TODO("Not yet implemented")
    }

    override fun initializeComponents(view: View, savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun initializeData() {
        TODO("Not yet implemented")
    }

    override fun initializeActions() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigateToHome.observe(this, Observer {
            // user is doing login inside home.
        })
    }
}