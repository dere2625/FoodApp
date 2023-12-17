import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
//import com.example.foodapp.Recipe
import com.example.foodapp.adapter.RecipesAdapter
//import com.example.foodapp.fragment.RecipeDetailBottomSheetFragment
import com.example.foodapp.sharedpreference.RecipeStorage
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecipesFragment : Fragment() {


    private lateinit var recipeAdapter: RecipesAdapter
    private val recipesList = mutableListOf<Recipe>()
    private lateinit var recipeSharedPreferences: RecipeStorage
    private val PICK_IMAGE_REQUEST_CODE = 1001
    private lateinit var imageViewRecipe: ImageView
    private var selectedImageUri: Uri? = null
        private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)
        recipeSharedPreferences = RecipeStorage(requireContext())
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewRecipes)
        recipeAdapter = RecipesAdapter(recipesList)
        recyclerView.adapter = recipeAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val fabAddMealPlan = view.findViewById<FloatingActionButton>(R.id.addNewRecipe)
        fabAddMealPlan.setOnClickListener {
            showAddRecipeDialog()
        }

        loadRecipesFromSharedPreferences()

        return view
    }


    private fun showAddRecipeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_recipe, null)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add New Recipe")

        val recipeNameEditText = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val cookingTimeEditText = dialogView.findViewById<EditText>(R.id.editTextCookingTime)
        val descriptionEditText = dialogView.findViewById<EditText>(R.id.editTextDescription)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.ratingBar)

        dialogBuilder.setPositiveButton("Add") { dialog, _ ->
            val recipeName = recipeNameEditText.text.toString()
            val cookingTime = cookingTimeEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val rating = ratingBar.rating

            val newRecipe = Recipe(recipeName, cookingTime, description,  selectedImageUri.toString() ,rating)
            recipesList.add(newRecipe)
            saveRecipes()
            recipeAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun saveRecipes() {
        recipeSharedPreferences.saveRecipes(recipesList)
    }

    private fun loadRecipesFromSharedPreferences() {
        recipesList.clear()
        recipesList.addAll(recipeSharedPreferences.getRecipes())
        recipeAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri? = data?.data
            if (::imageViewRecipe.isInitialized) {
                imageViewRecipe.setImageURI(selectedImage)
                selectedImageUri = selectedImage
            }
        }
    }
}