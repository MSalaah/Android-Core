package com.cl.core.base

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Abstract class for a Use Case
 * This class represents an execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link BaseFetchObserver}
 * that will execute its job in a background thread (or subscribeOnThread param) and will post the result in the UI thread(or postExecutionThread param).
 * <p>
 * Model type
 */
abstract class BaseUseCase<M> internal constructor() {

    private var subscribeOnThread: Scheduler
    private var postExecutionThread: Scheduler
    private var baseFetchObserver: BaseFetchObserver<M>? = null

    init {
        this.subscribeOnThread = Schedulers.newThread()
        this.postExecutionThread = AndroidSchedulers.mainThread()
    }

    /**
     * Builds an [Observable] which will be used when executing
     * the current [BaseUseCase].
     *
     */
    protected abstract fun buildUseCaseObservable(): Observable<M>

    /**
     * Executes the current use case.
     *
     * @param observer [BaseFetchObserver] which will be listening to the observable build
     * by [.buildUseCaseObservable] ()} method.
     */
    fun execute(observer: BaseFetchObserver<M>) {
        baseFetchObserver = observer
        val observable = returnObservable()
        observable.subscribe(observer)
    }

    fun reExecute() {
        val observable = returnObservable()
        observable.subscribeWith(baseFetchObserver!!)
    }

    private fun returnObservable(): Observable<M> {
        return this.buildUseCaseObservable()
            .subscribeOn(subscribeOnThread)
            .observeOn(postExecutionThread)
    }

    fun destroy() {
        if (baseFetchObserver != null) {
            baseFetchObserver!!.destroy()
            baseFetchObserver = null
        }
    }
}
