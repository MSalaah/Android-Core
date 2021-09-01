package com.cl.core.network

import io.reactivex.Observable
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import java.io.IOException
import java.lang.reflect.Type

class RxCallAdapterWrapper<R> internal constructor(private val _wrappedCallAdapter: CallAdapter<R, *>) : CallAdapter<R, Observable<R>> {

    override fun responseType(): Type = _wrappedCallAdapter.responseType()

    override fun adapt(call: Call<R>): Observable<R> {
        return (_wrappedCallAdapter.adapt(call) as Observable<R>).onErrorResumeNext { throwable: Throwable ->
            Observable.error<R>(asRetrofitException(throwable))
        }
    }

    private fun asRetrofitException(throwable: Throwable): ApiException {
        val apiException: ApiException
        if (throwable is HttpException) {
            apiException = handleApiErrors(throwable)
        } else if (throwable is IOException) {
            apiException = ApiException(-1, throwable.message, ErrorType.NETWORK)
        } else {
            apiException = ApiException(-1, throwable.message, ErrorType.UNEXPECTED)
        }

        return apiException
    }

    private fun handleApiErrors(httpException: HttpException): ApiException {
        var apiException: ApiException
        try {
            val jObjError = JSONObject(httpException.response().errorBody()!!.string())
                .getJSONObject("error")
            val messsage = jObjError.getString("message")
            apiException = ApiException(
                401,
                messsage, ErrorType.HTTP
            )
        } catch (ex: JSONException) {
            apiException = ApiException(-1, httpException.message, ErrorType.HTTP)
            //            Timber.e(ex);
        } catch (e: Exception) {
            apiException = ApiException(-1, httpException.message, ErrorType.HTTP)
            //            Timber.e(e);
        }

        return apiException
    }
}