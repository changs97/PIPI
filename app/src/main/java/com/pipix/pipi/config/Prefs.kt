package com.pipix.pipi.config

import android.content.Context
import android.content.Context.MODE_PRIVATE


class Prefs(context: Context) {
    private val prefNm="mPref"
    private val prefs=context.getSharedPreferences(prefNm,MODE_PRIVATE)
    var userName:String?
        get() = prefs.getString("sharedUserNameKey",null)
        set(value){
            prefs.edit().putString("sharedUserNameKey",value).apply()
        }
    var userId:String?
        get() = prefs.getString("sharedIDKey",null)
        set(value){
            prefs.edit().putString("sharedIDKey",value).apply()
        }

}