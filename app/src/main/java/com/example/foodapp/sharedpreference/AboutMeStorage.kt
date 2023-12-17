package com.example.foodapp.sharedpreference

import android.content.Context
import android.preference.PreferenceManager
import com.example.foodapp.models.AboutMeData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AboutMeStorage {
    private const val ABOUT_ME_KEY = "about_me_key"

    fun saveAboutMeData(context: Context, aboutMeList: List<AboutMeData>) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(aboutMeList)
        editor.putString(ABOUT_ME_KEY, json)
        editor.apply()
    }

    fun getAboutMeData(context: Context): MutableList<AboutMeData> {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = prefs.getString(ABOUT_ME_KEY, null)
        val type = object : TypeToken<MutableList<AboutMeData>>() {}.type

        return gson.fromJson(json, type) ?: mutableListOf()
    }
}
