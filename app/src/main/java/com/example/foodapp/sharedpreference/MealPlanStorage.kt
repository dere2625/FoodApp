package com.example.foodapp.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import com.example.foodapp.models.Meal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealPlanSharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MealPlanPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val type = object : TypeToken<List<Meal>>() {}.type
    private val editor = sharedPreferences.edit()

    fun saveMealPlan(meal: Meal) {
        val mealPlans = getMealPlans().toMutableList()
        mealPlans.add(meal)
        val json = gson.toJson(mealPlans)
        editor.putString("mealPlans", json)
        editor.apply()
    }

    fun getMealPlans(): List<Meal> {
        val json = sharedPreferences.getString("mealPlans", null)
        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}
