package com.pipix.pipi.src.fragment.logged_out.login.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("patients")
    val patients: List<TestResult>?
        )