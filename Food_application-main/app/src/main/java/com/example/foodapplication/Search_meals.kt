package com.example.foodapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.example.foodapplication.classes.AppDatabase
import com.example.foodapplication.classes.Meal
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Search_meals : AppCompatActivity() {

    lateinit var search_button : Button
    lateinit var ingred_et : EditText
    lateinit var allSavedMeals :List<Meal>
    lateinit var selectedMeal:MutableList<Meal>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meals)

        // create or connect  the database
        val db = Room.databaseBuilder(this, AppDatabase::class.java,
            "mydatabase").build()
        val MealDao = db.MealDao()

        runBlocking {
            launch {
                allSavedMeals = MealDao.getAll()
            }
        }

        selectedMeal = mutableListOf()

        search_button = findViewById<Button>(R.id.retrieve_button)
        ingred_et = findViewById<EditText>(R.id.ingredient_et)

        search_button.setOnClickListener {
            val mealBasedOnName = ArrayList<Meal>()
            val mealBasedOnIngredients = ArrayList<Meal>()
            for (meal in allSavedMeals){
                // Using contains function to find a string within another string
                //base on meal name
                if (meal.name?.contains(ingred_et.text.toString(),ignoreCase = true) == true){
                    mealBasedOnName.add(meal)
                }
                println(mealBasedOnName)
                
                //base on meal ingredients
                val listofingredients = arrayListOf(meal.Ingredient1,meal.Ingredient2,meal.Ingredient3, meal.Ingredient4, meal.Ingredient5, meal.Ingredient6, meal.Ingredient7, meal.Ingredient8, meal.Ingredient9, meal.Ingredient10, meal.Ingredient11, meal.Ingredient12, meal.Ingredient13, meal.Ingredient14, meal.Ingredient15, meal.Ingredient16, meal.Ingredient17,meal.Ingredient18, meal.Ingredient19, meal.Ingredient20)
                for (ingredient in listofingredients){
                    if (ingredient != null) {
                        if (ingredient.contains(ingred_et.text.toString(),ignoreCase = true)){
                            mealBasedOnIngredients.add(meal)
                            break
                        }

                    }
                }
            }
            println(mealBasedOnIngredients)
            selectedMeal = (mealBasedOnIngredients+mealBasedOnName).distinct().toMutableList()
            println(selectedMeal)
        }

    }
}