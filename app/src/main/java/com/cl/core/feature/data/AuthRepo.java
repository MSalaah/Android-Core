package com.cl.core.feature.data;

import com.webkeyz.mvp.auth.model.LoginResponse;

import io.reactivex.Observable;

public interface AuthRepo {

    Observable<LoginResponse> doLogin(String email, String password);

}
