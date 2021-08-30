package com.cl.core.base

import android.util.SparseArray
import androidx.core.util.forEach
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cl.core.annotation.UseCase
import com.cl.core.helpers.LoadingIndicator
import com.cl.core.network.ApiException
import java.lang.reflect.Field
import java.util.*

open class BaseViewModel : ViewModel() {

    private var useCases: SparseArray<BaseUseCase<*>>? = null
    private val failedUseCasesList = ArrayList<BaseUseCase<*>>()


    val loadingIndicatorLiveData = MutableLiveData<LoadingIndicator>()

    val failResponseLiveData = MutableLiveData<ApiException>()


    override fun onCleared() {
        super.onCleared()
        if (useCases != null) {
            useCases!!.forEach { _, value ->
                value.destroy()
            }
        }
    }

    open fun start() {
        fillAllUseCasesList()
    }

    /**
     * Read all [.useCases] annotated with useCase annotation and add them to local list
     */
    private fun fillAllUseCasesList() {
        try {
            useCases = retrieveDeclaredUSeCases()
        } catch (e: IllegalAccessException) {
            print(e)
        }
    }

    /**
     * Read all useCases annotated with useCase annotation.
     */
    @Throws(IllegalAccessException::class)
    private fun retrieveDeclaredUSeCases(): SparseArray<BaseUseCase<*>> {
        val useCases: SparseArray<BaseUseCase<*>> = SparseArray()
        for (field in this.javaClass.declaredFields) {

            if (isAnnotatedWithUseCaseAnnotation(field, UseCase::class.java)) {
                field.isAccessible = true
                val id = readAnnotationValue(field, UseCase::class.java)
                useCases.put(id, field.get(this) as BaseUseCase<*>)
            }
        }
        return useCases
    }

    private fun isAnnotatedWithUseCaseAnnotation(field: Field, annotation: Class<UseCase>): Boolean {
        return field.isAnnotationPresent(annotation)
    }

    private fun readAnnotationValue(field: Field, annotation: Class<UseCase>): Int {
        return field.getAnnotation(annotation).value
    }

    /**
     * this will restart all failed useCases.
     */
    open fun restart() {
        executeAllFailedUseCases()
    }


    private fun executeAllFailedUseCases() {
        for (useCase in failedUseCasesList) {
            useCase.reExecute()
        }
        failedUseCasesList.clear()
    }

    fun handleError(error: ApiException, id: Int) {
        //add to fail list
        addUseCaseToFailedList(id)
        // expose error
        failResponseLiveData.value = error
    }

    fun doOnNext() {
        failResponseLiveData.value = null
    }

    /**
     * keeps reference of this useCase to reExecute it when presenter restarts.
     *
     * @param id id of the failed useCase.
     */
    private fun addUseCaseToFailedList(id: Int) {
        if (useCases != null && useCases!!.size() > 0) {
            val useCase = useCases!!.get(id)
            if (useCase != null) {
                failedUseCasesList.add(useCase)
            }
        }
    }
}
