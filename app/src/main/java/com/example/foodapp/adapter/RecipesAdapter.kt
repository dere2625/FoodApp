package com.example.foodapp.adapter

import Recipe
import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R

class RecipesAdapter(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeName: TextView = itemView.findViewById(R.id.textRecipeTitle)
        private val instructions: TextView = itemView.findViewById(R.id.textRecipeDescription)
        private val recipeCookingTime: TextView = itemView.findViewById(R.id.textRecipeCookingTime)
        private val ratinng : RatingBar = itemView.findViewById(R.id.ratingBar)

        @SuppressLint("SetTextI18n")
        fun bind(recipe: Recipe) {
            recipeName.text = "Recipe Name: ${recipe.title}"
            recipeCookingTime.text = "Cooking time: ${recipe.description}"
            instructions.text = "Recipe Description: ${recipe.cookingTime}"
            ratinng.rating = recipe.rating
        }
    }

}

