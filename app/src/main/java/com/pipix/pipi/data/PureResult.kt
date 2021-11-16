package com.pipix.pipi.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "pure_result_table")
data class PureResult (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tpaRight: Int,
    val tpaLeft: Int,
    val date: String,
    
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
    val L_8000: Int,
    val personId: Int
) : Serializable

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

@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tpaRight: Int,
    val tpaLeft: Int,
    val date: String,

    val scoreRight: Int,
    val scoreLeft: Int,
    val personId: Int
)