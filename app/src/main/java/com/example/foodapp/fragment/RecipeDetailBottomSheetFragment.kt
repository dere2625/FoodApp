//package com.example.foodapp.fragment
//
//import Recipe
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.RatingBar
//import android.widget.TextView
//import com.example.foodapp.R
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment
//
//class RecipeDetailBottomSheetFragment : BottomSheetDialogFragment() {
//
//    companion object {
//        private const val ARG_RECIPE = "arg_recipe"
//
//        fun newInstance(recipe: Recipe): RecipeDetailBottomSheetFragment {
//            val fragment = RecipeDetailBottomSheetFragment()
//            val args = Bundle()
//            args.putParcelable(ARG_RECIPE, recipe)
//            fragment.arguments = args
//            return fragment
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.layout_recipe_detail, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        val recipe = arguments?.getParcelable<Recipe>(ARG_RECIPE)
//        if (recipe != null) {
//            val im = recipe.imageResource.toInt()
//            view.findViewById<ImageView>(R.id.imageRecipe).setImageResource(im)
//            view.findViewById<TextView>(R.id.textRecipeTitle).text = recipe.title
//            view.findViewById<TextView>(R.id.textCookingTime).text = "Cooking Time: ${recipe.cookingTime}"
//            view.findViewById<TextView>(R.id.textCookingTime).text = "Cooking Instruction: ${recipe.description}"
//            view.findViewById<RatingBar>(R.id.ratingBar).rating = recipe.rating
//
//
//
//        }
//    }
//}
//
//
//
