package com.pipix.pipi.src.fragment.insertPerson.model

import com.google.gson.annotations.SerializedName

data class InsertResponse(
    @SerializedName("address")
    val address: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("caregiverId")
    val caregiverId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageURL")
    val imageURL: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("schedule")
    val schedule: Schedule?,
    @SerializedName("sex")
    val sex: String,
    @SerializedName("testResults")
    val testResults: List<TestResult>?
    )