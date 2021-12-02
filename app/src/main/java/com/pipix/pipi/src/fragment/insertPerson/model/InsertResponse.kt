package com.pipix.pipi.src.fragment.insertPerson.model

data class InsertResponse(
    val address: String,
    val age: Int,
    val caregiverId: Int,
    val id: Int,
    val imageURL: Any,
    val name: String,
    val schedule: Any,
    val sex: String,
    val testResults: Any
)