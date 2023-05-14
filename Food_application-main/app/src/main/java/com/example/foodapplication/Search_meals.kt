package com.example.foodapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import android.widget.Toast
import androidx.room.Room
import com.example.foodapplication.classes.AppDatabase
import com.example.foodapplication.classes.Meal
import com.example.foodapplication.utils.GridRVAdeptor_MiniMealCards
import com.example.foodapplication.utils.GridViewModal
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Search_meals : AppCompatActivity() {

    lateinit var search_button : Button
    lateinit var ingred_et : EditText
    lateinit var allSavedMeals :List<Meal>
    lateinit var selectedMeal:MutableList<Meal>

    lateinit var miniCardLayout: GridView
    lateinit var miniCardList: List<GridViewModal>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meals)

        // create or connect  the database
        val db = Room.databaseBuilder(this, AppDatabase::class.java,
            "mydatabase").build()
        val MealDao = db.MealDao()

        //retrieves all saved meals from the database using a coroutine and stores them in the 'allSavedMeals' variable.
        runBlocking {
            launch {
                allSavedMeals = MealDao.getAll()
            }
        }

        selectedMeal = mutableListOf()

        search_button = findViewById<Button>(R.id.retrieve_button)
        ingred_et = findViewById<EditText>(R.id.ingredient_et)

        search_button.setOnClickListener {
            //keyboard hide
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(ingred_et.windowToken, 0)

            val mealBasedOnName = ArrayList<Meal>()
            val mealBasedOnIngredients = ArrayList<Meal>()
            selectedMeal.clear()

           if (ingred_et.text.isNotEmpty()){
               for (meal in allSavedMeals){
                   // Using contains function to find a string within another string
                   //based on the meal name
                   if (meal.name?.contains(ingred_et.text.toString(),ignoreCase = true) == true){
                       mealBasedOnName.add(meal)
                   }

                   //base on the ingredients
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
               selectedMeal = (mealBasedOnIngredients+mealBasedOnName).distinct().toMutableList()
               if (selectedMeal.size==0){
                   //show the toast
                   fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
                       Toast.makeText(context, message, duration).show()
                   }

                   showToast(this, "Meal Or Ingredient is Not Found!")
               }


           }
           else{
               selectedMeal.clear()
               //show the toast
               fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
                   Toast.makeText(context, message, duration).show()
               }

               showToast(this, "Please Enter Meal Or Ingredients!")
           }
            createMiniCard()
        }

        //when press search keyboard enter button
        ingred_et.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //keyboard hide
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(ingred_et.windowToken, 0)
                val mealBasedOnName = ArrayList<Meal>()
                val mealBasedOnIngredients = ArrayList<Meal>()
                if (ingred_et.text.isNotEmpty()){
                    for (meal in allSavedMeals){
                        // Using contains function to find a string within another string
                        //based on meal name
                        if (meal.name?.contains(ingred_et.text.toString(),ignoreCase = true) == true){
                            mealBasedOnName.add(meal)
                        }

                        //based on meal ingredients
                        val listofingredients = arrayListOf(meal.Ingredient1,meal.Ingredient2,meal.Ingredient3, meal.Ingredient4, meal.Ingredient5, meal.Ingredient6, meal.Ingredient7, meal.Ingredient8, meal.Ingredient9, meal.Ingredient10, meal.Ingredient11, meal.Ingredient12, meal.Ingredient13, meal.Ingredient14, meal.Ingredient15, meal.Ingredient16, meal.Ingredient17,meal.Ingredient18, meal.Ingredient19, meal.Ingredient20)
                        for (ingredient in listofingredients){
                            if (ingredient != null) {
                                // if an ingredient in a list of meals contains the inputted ingredient, and adds the meal to a list if it does.
                                if (ingredient.contains(ingred_et.text.toString(),ignoreCase = true)){
                                    mealBasedOnIngredients.add(meal)
                                    break
                                }

                            }
                        }
                    }
                    // assigns the list of meals based on ingredients and name to selectedMeal, remove duplicates and print the resulting list.
                    selectedMeal = (mealBasedOnIngredients+mealBasedOnName).distinct().toMutableList()
                    println(selectedMeal)

                    createMiniCard()
                }
                else{
                    //show the toast
                    fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
                        Toast.makeText(context, message, duration).show()
                    }

                    showToast(this, "Please Enter Meal Or Ingredients!")
                }


                return@OnKeyListener true
            }
            false
        })

    }

    private fun createMiniCard() {
        // initializing variables of grid view with their ids.
        miniCardLayout = findViewById(R.id.minicard_grid_layout)
        miniCardList = ArrayList<GridViewModal>()

        // on below line adding data to meal list with image and meal name.
        for (meal in selectedMeal) {
            println("reched")
            miniCardList = miniCardList + GridViewModal(meal)
        }
        val courseAdapter = GridRVAdeptor_MiniMealCards(courseList = miniCardList, this@Search_meals)
        // on below line we are setting adapter to our grid view.
        miniCardLayout.adapter = courseAdapter
    }

    //saves the selectedMeal object as an ArrayList in the bundle "outState".
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("selectedMeal", selectedMeal as ArrayList) //outstate saves the key-value pairs of data
    }

    //
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        selectedMeal = savedInstanceState.getSerializable("selectedMeal") as MutableList<Meal>
        createMiniCard()

    }

}