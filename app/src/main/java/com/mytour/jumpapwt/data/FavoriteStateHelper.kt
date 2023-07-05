package com.mytour.jumpapwt.data

import android.content.Context

class FavoriteStateHelper(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("FavoriteState", Context.MODE_PRIVATE)

    fun isFavorite(tourismName: String): Boolean {
        return sharedPreferences.getBoolean(tourismName, false)
    }

    fun setFavorite(tourismName: String, isFavorite: Boolean) {
        sharedPreferences.edit().putBoolean(tourismName, isFavorite).apply()
    }
}