package com.cl.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {


    private var loadingIndicator: Dialog? = null
    lateinit var viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragmentView = inflater.inflate(getContentLayout(), container, false)

        initializeComponents(fragmentView, savedInstanceState)

        initializeViewModel()

        initializeData()

        initializeActions()

        observeError()

        observeLoading()

        return fragmentView
    }

    /**
     * Method to associate the UI and ViewModel
     */
    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(requireActivity()).get(injectViewModel())
    }


    /**
     * Method that return the layout file id
     *
     * @return The Layout file id
     */
    protected abstract fun getContentLayout(): Int

    /**
     * Method responsible for getting the corresponding view model.
     *
     * @return The view model class.
     */
    protected abstract fun injectViewModel(): Class<VM>

    /**
     * Method responsible for ui components and controls
     */
    protected abstract fun initializeComponents(view: View, savedInstanceState: Bundle?)

    /**
     * Method responsible for ui data
     */
    protected abstract fun initializeData()

    /**
     * Method responsible for ui actions
     */
    protected abstract fun initializeActions()

    /**
     * Method responsible for observing api exceptions
     */
    private fun observeError() {
        viewModel.failResponseLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null)
                Toast.makeText(context, "${it.message}", Toast.LENGTH_LONG).show()
        })
    }

    /**
     * Method responsible for observing api exceptions
     */
    private fun observeLoading() {
        viewModel.loadingIndicatorLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isLoading)
//                showProgressLoadingIndicator()
            else
                hideProgressLoadingIndicator()
        })
    }

    /**
     * show progress indicator to indicates that the process is in processing.
     */
//    private fun showProgressLoadingIndicator() {
//        if (loadingIndicator == null && isAdded) {
//            loadingIndicator = Dialog(context!!)
//            loadingIndicator!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            loadingIndicator!!.setContentView(R.layout.loading)
//            loadingIndicator!!.setCancelable(false)
//            loadingIndicator!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            loadingIndicator!!.show()
//        }
//    }

    /**
     * Hide previously displayed progress indicator to indicates that the process is finished.
     */
    private fun hideProgressLoadingIndicator() {
        if (loadingIndicator != null) {
            loadingIndicator!!.dismiss()
            loadingIndicator = null
        }
    }
}