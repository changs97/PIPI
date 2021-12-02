package com.pipix.pipi.src.fragment.insertPerson.model

data class InsertBody(
    val address: String,
    val age: String,
    val imageURL: String?,
    val name: String,
    val sex: Int
)