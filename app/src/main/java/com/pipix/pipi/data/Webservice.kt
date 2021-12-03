package com.pipix.pipi.data

import com.pipix.pipi.src.fragment.insertPerson.model.InsertBody
import com.pipix.pipi.src.fragment.insertPerson.model.InsertResponse
import com.pipix.pipi.src.fragment.insertPerson.model.InsertScheduleBody
import com.pipix.pipi.src.fragment.insertPerson.model.InsertScheduleResponse
import com.pipix.pipi.src.fragment.logged_out.join.register_step2.model.SignUpBody
import com.pipix.pipi.src.fragment.logged_out.join.register_step2.model.SignUpResponse
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginBody
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginResponse
import com.pipix.pipi.src.fragment.modify.model.ModifyBody
import com.pipix.pipi.src.fragment.modify.model.ModifyResponse
import retrofit2.Call
import retrofit2.http.*

interface Webservice {

    @POST("/api/user/logIn")
    fun postLogin(@Body params: LoginBody): Call<LoginResponse>

    @POST("/api/user/signUp")
    fun postSignUp(@Body params: SignUpBody): Call<SignUpResponse>

    @POST("/api/patient/create/{userId}")
    fun postInsert(@Body params: InsertBody, @Path("userId") userId : Int ): Call<InsertResponse>

    @PUT("/api/patient/updateSchedule/{patientId}")
    fun putSchedule(@Body params: InsertScheduleBody, @Path("patientId") patientId : Int ): Call<InsertScheduleResponse>

    @PUT("/api/patient/update/{patientId}")
    fun putUpdate(@Body params: ModifyBody, @Path("patientId") patientId : Int ): Call<ModifyResponse>

}
