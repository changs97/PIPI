package com.pipix.pipi.data

import com.pipix.pipi.src.fragment.logged_out.join.register_step2.model.SignUpBody
import com.pipix.pipi.src.fragment.logged_out.join.register_step2.model.SignUpResponse
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginBody
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface Webservice {

    @GET("/api/user/login")
    fun getLogin(@Body params: LoginBody): Call<LoginResponse>

    @POST("/api/signUp")
    fun postSignUp(@Body params: SignUpBody): Call<SignUpResponse>


}
