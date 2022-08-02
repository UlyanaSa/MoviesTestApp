package com.osvin.moviestestapp.data.storage

import android.content.Context
import com.osvin.moviestestapp.utils.Constants

class SharedPreferences(context: Context){


    private var sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF,Context.MODE_PRIVATE)

    fun getSharedPref(){
        sharedPreferences
    }

    fun saveOffset(currentOffset: Int): Boolean {
        sharedPreferences.edit().putInt(Constants.OFFSET_SIZE, currentOffset).apply()
        return true
    }

    fun getCurrentOffset(): Int {
        val offset = sharedPreferences.getInt(Constants.OFFSET_SIZE,0)
        return offset
    }


}