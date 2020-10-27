package com.dupat.dupatmovie.data.session

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager {
    companion object{
        fun setToken(ctx: Context, key: String, value: String)
        {
            val pref = ctx.getSharedPreferences("MyRef",Context.MODE_PRIVATE)
            pref.edit().putString(key,value)
            pref.edit().apply()
        }

        fun getToken(ctx: Context, key: String) : String
        {
            val pref = ctx.getSharedPreferences("MyRef",Context.MODE_PRIVATE)
            return pref.getString(key,"Undefined")!!
        }

        fun clearSession(ctx: Context)
        {
            val pref = ctx.getSharedPreferences("MyRef",Context.MODE_PRIVATE)
            pref.edit().clear().apply()
        }
    }
}