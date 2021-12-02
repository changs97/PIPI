package com.pipix.pipi.src.fragment.logged_out.login.model

data class LoginResponse (
    val email: String,
    val id: Int,
    val name: String,
    val patients: List<Any>
        )