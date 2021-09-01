package com.cl.core.base


import com.cl.core.helpers.LoadingIndicator
import com.cl.core.network.ApiException
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BaseFetchObserver<M> : Observer<M> {

    private lateinit var baseViewModel: WeakReference<BaseViewModel>
    private lateinit var disposables: CompositeDisposable
    private var id: Int = 0
    private lateinit var loadingIndicator: LoadingIndicator

    /**
     * @param baseViewModel    to handle call backs.
     * @param id               id of the useCase that use this observer.
     * @param loadingIndicator
     */
    constructor(baseViewModel: BaseViewModel, id: Int, loadingIndicator: LoadingIndicator) : this(
        baseViewModel
    ) {
        this.id = id
        this.loadingIndicator = loadingIndicator
    }

    constructor(id: Int) : super() {
        this.id = id
        this.disposables = CompositeDisposable()
    }

    /**
     * @param baseViewModel to handle call backs.
     */
    private constructor(baseViewModel: BaseViewModel) {
        this.baseViewModel = WeakReference(baseViewModel)
        this.disposables = CompositeDisposable()
    }

    override fun onError(exception: Throwable) {
        loadingIndicator.isLoading = false
        baseViewModel.get()!!.loadingIndicatorLiveData.value = (loadingIndicator)

        if (exception is ApiException)
            baseViewModel.get()!!.handleError(exception, id)
    }

    override fun onNext(@io.reactivex.annotations.NonNull m: M) {
        baseViewModel.get()!!.doOnNext()
    }

    override fun onSubscribe(@io.reactivex.annotations.NonNull d: Disposable) {
        addDisposable(d)
    }

    override fun onComplete() {
        destroy()
    }

    /**
     * Dispose from current [CompositeDisposable] and destroy any other references if found
     */
    fun destroy() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     *
     * @param disposable
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
