package com.pipix.pipi.src.fragment.hearingTest.model

import com.google.gson.annotations.SerializedName

data class TestResponse(
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("l_1000")
    val l_1000: Int,
    @SerializedName("l_2000")
    val l_2000: Int,
    @SerializedName("l_250")
    val l_250: Int,
    @SerializedName("l_4000")
    val l_4000: Int,
    @SerializedName("l_500")
    val l_500: Int,
    @SerializedName("l_8000")
    val l_8000: Int,
    @SerializedName("patientId")
    val patientId: Int,
    @SerializedName("r_1000")
    val r_1000: Int,
    @SerializedName("r_2000")
    val r_2000: Int,
    @SerializedName("r_250")
    val r_250: Int,
    @SerializedName("r_4000")
    val r_4000: Int,
    @SerializedName("r_500")
    val r_500: Int,
    @SerializedName("r_8000")
    val r_8000: Int,
    @SerializedName("tpaLeft")
    val tpaLeft: Int,
    @SerializedName("tpaRight")
    val tpaRight: Int
)