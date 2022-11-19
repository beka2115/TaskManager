package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class Pref(context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences("pref_name", MODE_PRIVATE)
    fun isOnBoardingShow(): Boolean {
        return pref.getBoolean(BOARDING_SHOW, false)
    }

    fun saveShowBoarding(isShow: Boolean) {
        pref.edit().putBoolean(BOARDING_SHOW, isShow).apply()
    }

    fun saveEditText(name: String) {
        pref.edit().putString(SAVE_EDIT_TEXT, name).apply()
    }

    fun takeEditText(): String? {
        return pref.getString(SAVE_EDIT_TEXT, "")
    }

    fun savePicture(picture: String) {
        pref.edit().putString(PROFILE_PICTURE_KEY, picture).apply()
    }

    fun takePicture(): String? {
        return pref.getString(PROFILE_PICTURE_KEY, "")
    }

    fun saveEditNum(num: String) {
        pref.edit().putString(SAVE_EDIT_NUMBER, num).apply()
    }

    fun takeNumber(): String? {
        return pref.getString(SAVE_EDIT_NUMBER, "")
    }

    companion object {
        private const val BOARDING_SHOW = "on_boarding_show"
        private const val SAVE_EDIT_TEXT = "save.text"
        private const val SAVE_EDIT_NUMBER = "save.number"
        private const val PROFILE_PICTURE_KEY = "save.picture"
    }

}