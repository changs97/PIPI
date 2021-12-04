package com.pipix.pipi.src.fragment.logged_out.login.model

data class Patient(
    val address: String,
    val age: Int,
    val caregiverId: Int,
    val id: Int,
    val imageURL: String?,
    val name: String,
    val schedule: Schedule?,
    val sex: String,
    val testResults: List<TestResult>
)