package com.pipix.pipi.src.fragment.modify

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ModifyOld(
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

) : Parcelable