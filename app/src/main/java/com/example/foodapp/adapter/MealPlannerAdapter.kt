package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.models.Meal
import com.example.foodapp.sharedpreference.MealPlanSharedPreferences

//class MealPlannerAdapter(private val mealPlans: List<Meal>) :
//    RecyclerView.Adapter<MealPlannerAdapter.MealPlanViewHolder>() {
//    private val mealPlanList = mutableListOf<Meal>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPlanViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_meal_plan, parent, false)
//        return MealPlanViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MealPlanViewHolder, position: Int) {
//        val mealPlan = mealPlanList[position]
//        holder.bind(mealPlan)
//    }
//
//    override fun getItemCount(): Int = mealPlanList.size
//
//    inner class MealPlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(mealPlan: Meal) {
//            // Bind meal plan data to the views in the item layout
//            itemView.findViewById<TextView>(R.id.textViewDay).text = mealPlan.day
//            itemView.findViewById<TextView>(R.id.textViewBreakfast).text = mealPlan.breakfast
//            itemView.findViewById<TextView>(R.id.textViewLunch).text = mealPlan.lunch
//            itemView.findViewById<TextView>(R.id.textViewDinner).text = mealPlan.dinner
//        }
//    }
//}

class MealPlannerAdapter(
    private val mealPlans: MutableList<Meal>,
    private val mealPlanSharedPreferences: MealPlanSharedPreferences
) : RecyclerView.Adapter<MealPlannerAdapter.MealPlanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPlanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_plan, parent, false)
        return MealPlanViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealPlanViewHolder, position: Int) {
        val mealPlan = mealPlans[position]
        holder.bind(mealPlan)
//        holder.bindDeleteButton(mealPlan, position)
    }

    override fun getItemCount(): Int = mealPlans.size

//    fun removeMealPlan(position: Int) {
//        mealPlanSharedPreferences.removeMealPlan(mealPlans[position])
//        mealPlans.removeAt(position)
//        notifyItemRemoved(position)
//    }

    inner class MealPlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayOfWeekTextView: TextView = itemView.findViewById(R.id.textViewDay)
        private val breakfastTextView: TextView = itemView.findViewById(R.id.textViewBreakfast)
        private val lunchTextView: TextView = itemView.findViewById(R.id.textViewLunch)
        private val dinnerTextView: TextView = itemView.findViewById(R.id.textViewDinner)
//        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

        fun bind(dayMealPlan: Meal) {
            dayOfWeekTextView.text = "Day : ${   dayMealPlan.day}"

            breakfastTextView.text = "BreakFast : ${    dayMealPlan.breakfast}"

            lunchTextView.text = "Lunch : ${    dayMealPlan.lunch}"

            dinnerTextView.text = "Dinner : ${   dayMealPlan.dinner}"

        }

//        fun bindDeleteButton(mealPlan: DayMealPlan, position: Int) {
//            deleteButton.setOnClickListener {
//                removeMealPlan(position)
//            }
//        }
    }
}

