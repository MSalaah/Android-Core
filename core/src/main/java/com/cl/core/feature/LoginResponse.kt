package com.webkeyz.mvp.auth.model

import com.google.gson.annotations.SerializedName

class LoginResponse {

    @SerializedName("type")
    var type: String? = null

    @SerializedName("access_token")
    var authToken: String? = null

    @SerializedName("newUser")
    var newUser: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("name")
    var name: String? = null
}