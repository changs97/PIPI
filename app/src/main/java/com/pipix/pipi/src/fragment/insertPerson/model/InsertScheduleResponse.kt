package com.pipix.pipi.src.fragment.insertPerson.model

import com.google.gson.annotations.SerializedName

data class InsertScheduleResponse(
    @SerializedName("fri")
    val fri: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mon")
    val mon: String?,
    @SerializedName("patientId")
    val patientId: Int,
    @SerializedName("sat")
    val sat: String?,
    @SerializedName("sun")
    val sun: String?,
    @SerializedName("thu")
    val thu: String?,
    @SerializedName("tue")
    val tue: String?,
    @SerializedName("wed")
    val wed: String?
)