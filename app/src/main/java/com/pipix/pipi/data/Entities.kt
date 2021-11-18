package com.pipix.pipi.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(tableName = "pure_result_table")
data class PureResult (
    @PrimaryKey(autoGenerate = true)
    val oldID: Int,
    val userID: String,
    val date: Date,
    val tpaRight: Int,
    val tpaLeft: Int,

    val R_250: Int,
    val R_500: Int,
    val R_1000: Int,
    val R_2000: Int,
    val R_4000: Int,
    val R_8000: Int,

    val L_250: Int,
    val L_500: Int,
    val L_1000: Int,
    val L_2000: Int,
    val L_4000: Int,
    val L_8000: Int
) : Serializable


@Entity(tableName = "old_table")
data class Old(
    @PrimaryKey(autoGenerate = true)
    val oldID: Int,
    val userID: String,

    val oldName: String,
    val oldAge: Int,
    val oldSex: Int,
    val oldAddress: String,
    val oldImage: String?, // nullable

    val mon: String?, // nullable
    val tue: String?, // nullable
    val wed: String?, // nullable
    val thu: String?, // nullable
    val fri: String?, // nullable
    val sat: String?, // nullable
    val sun: String?, // nullable

)


@Entity(tableName = "speech_result_table")
data class SpeechResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tpaRight: Int,
    val tpaLeft: Int,
    val date: String,
    val scoreRight: Int,
    val scoreLeft: Int,
    val personId: Int
)