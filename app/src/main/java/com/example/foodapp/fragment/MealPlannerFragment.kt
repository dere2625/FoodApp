package com.example.foodapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.adapter.MealPlannerAdapter
import com.example.foodapp.models.Meal
import com.example.foodapp.sharedpreference.MealPlanSharedPreferences
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MealPlannerFragment : Fragment() {
    private lateinit var mealPlanAdapter: MealPlannerAdapter
    private lateinit var mealPlanSharedPreferences: MealPlanSharedPreferences
    private val mealPlanList = mutableListOf<Meal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_planner, container, false)

        mealPlanSharedPreferences = MealPlanSharedPreferences(requireContext())
        mealPlanList.clear()
        mealPlanList.addAll(mealPlanSharedPreferences.getMealPlans())

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewMealPlan)
        mealPlanAdapter = MealPlannerAdapter(mealPlanList, mealPlanSharedPreferences)
        recyclerView.adapter = mealPlanAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val fabAddMealPlan = view.findViewById<FloatingActionButton>(R.id.fabAddMealPlan)
        fabAddMealPlan.setOnClickListener {
            showAddMealPlanDialog()
        }

        return view
    }


    private fun showAddMealPlanDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_meal, null)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Meal Plan")

        val editTextDay = dialogView.findViewById<DatePicker>(R.id.date_Picker)
        val editTextBreakfast = dialogView.findViewById<EditText>(R.id.editTextBreakfast)
        val editTextLunch = dialogView.findViewById<EditText>(R.id.editTextLunch)
        val editTextDinner = dialogView.findViewById<EditText>(R.id.editTextDinner)

        dialogBuilder.setPositiveButton("Save") { dialog, _ ->
            val day = editTextDay.dayOfMonth.toString() +"-"+ editTextDay.month.toString() +"-"+ editTextDay.year.toString()
            val breakfast = editTextBreakfast.text.toString()
            val lunch = editTextLunch.text.toString()
            val dinner = editTextDinner.text.toString()
            val newMealPlan = Meal(day, breakfast, lunch, dinner)
            mealPlanList.add(newMealPlan)
            saveMealPlan(newMealPlan)
            mealPlanAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun saveMealPlan(mealPlan: Meal) {
        val mealPlanSharedPreferences = MealPlanSharedPreferences(requireContext())
        mealPlanSharedPreferences.saveMealPlan(mealPlan)
    }

    private fun loadMealPlansFromSharedPreferences() {
        val mealPlanSharedPreferences = MealPlanSharedPreferences(requireContext())
        mealPlanList.clear()
        mealPlanList.addAll(mealPlanSharedPreferences.getMealPlans())
        mealPlanAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        loadMealPlansFromSharedPreferences()

    }

}