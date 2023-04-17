package com.example.foodapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.room.Room
import com.example.foodapplication.classes.AppDatabase
import com.example.foodapplication.classes.Meal
import com.example.foodapplication.utils.GridRVAdeptor_MealCards
import com.example.foodapplication.utils.GridViewModal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Search_by_ingredients : AppCompatActivity() {
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>
    lateinit var save: Button
    var isAllsaved_button=false
    lateinit var meal_id:EditText
    var allMeal = arrayListOf<Meal>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_by_ingredients)

        val db = Room.databaseBuilder(
            this, AppDatabase::class.java,
            "mydatabase"
        ).build()
        val MealDao = db.MealDao()



        meal_id = findViewById<EditText>(R.id.ingredient_et)
        save = findViewById<Button>(R.id.save_db_button)
        var retrieve = findViewById<Button>(R.id.retrieve_button)
        retrieve.isEnabled = false
        save.isEnabled = false


        meal_id.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                word: CharSequence, start: Int,
                before: Int, count: Int
            ) {

                if (word.isNotEmpty()) {
                    retrieve.isEnabled = true
                } else {
                    retrieve.isEnabled = false
                }
            }
        })
        retrieve.setOnClickListener {
            mealSearchFunction()
        }

        save.setOnClickListener {
            //show the toast
            fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
                Toast.makeText(context, message, duration).show()
            }

            showToast(this, "Meal added to the DataBase!")
            runBlocking {
                launch {

                    for (meal in allMeal) MealDao.insertMeal(meal)
                }

            }
            save.isEnabled = false
            isAllsaved_button=false
        }

        //
        //source = https://stackoverflow.com/questions/47298935/handling-enter-key-on-edittext-kotlin-android#:~:text=editText.setOnKeyListener(View.OnKeyListener%20%7B%20v%2C%20keyCode%2C%20event%20%2D%3E%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20if%20(keyCode%20%3D%3D%20KeyEvent.KEYCODE_ENTER%20%26%26%20event.action%20%3D%3D%20KeyEvent.ACTION_UP)%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20//Perform%20Code%20%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20return%40OnKeyListener%20true%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20false%0A%20%20%20%20%20%20%20%20%20%20%20%20%7D)
        meal_id.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                mealSearchFunction()
                return@OnKeyListener true
            }
            false
        })
    }

    private fun mealSearchFunction() {
        //keyboard hide
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(meal_id.windowToken, 0)
        //clear all pre used data
        allMeal.clear()
        save.isEnabled = true
        isAllsaved_button = true

        //collecting all JSON string
        val stb = StringBuilder()
        //changing the URl
        val url_string =
            "https://www.themealdb.com/api/json/v1/1/filter.php?i=" + meal_id.text.toString()
        var url = URL(url_string)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        runBlocking {
            launch {
                // run the code of the coroutine in a new thread
                withContext(Dispatchers.IO) {
                    var bf = BufferedReader(InputStreamReader(con.inputStream))
                    var line: String? = bf.readLine()
                    while (line != null) {
                        stb.append(line + "\n")
                        line = bf.readLine()
                    }
                    parseJSON(stb)
                }
            }
        }
        if (allMeal.size == 0)save.isEnabled = false
        //
        createCard()
    }

    private fun createCard() {
        // initializing variables of grid view with their ids.
        courseGRV = findViewById(R.id.grid_layout1)
        courseList = ArrayList<GridViewModal>()

        // on below line we are adding data to
        // our course list with image and course name.
        for (meal in allMeal) {
            courseList = courseList + GridViewModal(meal)
        }
        val courseAdapter = GridRVAdeptor_MealCards(courseList = courseList, this@Search_by_ingredients)

        // on below line we are setting adapter to our grid view.
        courseGRV.adapter = courseAdapter
    }

    suspend fun parseJSON(stb: java.lang.StringBuilder) {
// this contains the full JSON returned by the Web Service
        val json = JSONObject(stb.toString())
        if (json.isNull("meals")) {
            //show the toast
            runOnUiThread {
                Toast.makeText(
                    this@Search_by_ingredients,
                    "Ingredient is Not Available!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            // Information about all the meal extracted by this function
            allMeal.clear() //to reset the array when refreshing the next time
            var jsonArray: JSONArray = json.getJSONArray("meals")
            // extract all the meals from the JSON array
            for (i in 0 until jsonArray.length()) {
                val meal: JSONObject = jsonArray[i] as JSONObject // this is a json object

                //collecting all JSON string
                val stb = StringBuilder()
                //changing the URl
                val url_string =
                    "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + meal["idMeal"]
                val url = URL(url_string)
                val con: HttpURLConnection = url.openConnection() as HttpURLConnection
                runBlocking {
                    launch {
                        // run the code of the coroutine in a new thread
                        withContext(Dispatchers.IO) {
                            var bf = BufferedReader(InputStreamReader(con.inputStream))
                            var line: String? = bf.readLine()
                            while (line != null) {
                                stb.append(line + "\n")
                                line = bf.readLine()
                            }
                        }
                    }
                }

            }
// Information about all the meal extracted by this function
            allMeal.clear() //to reset the array when refreshing the next time
            var jsonArray2: JSONArray = json.getJSONArray("meals")
// extract all the meals from the JSON array
            for (i in 0 until jsonArray2.length()) {
                val meal: JSONObject = jsonArray2[i] as JSONObject // this is a json object

                //collecting all JSON string
                val stb = StringBuilder()
                //changing the URl
                val url_string =
                    "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + meal["idMeal"]
                val url = URL(url_string)
                val con: HttpURLConnection = url.openConnection() as HttpURLConnection
                runBlocking {
                    launch {
                        // run the code of the coroutine in a new thread
                        withContext(Dispatchers.IO) {
                            var bf = BufferedReader(InputStreamReader(con.inputStream))
                            var line: String? = bf.readLine()
                            while (line != null) {
                                stb.append(line + "\n")
                                line = bf.readLine()
                            }
                        }
                    }
                }

                val json = JSONObject(stb.toString())

                var jsonArray: JSONArray = json.getJSONArray("meals")
                var tempMeal: Meal? = null
                for (i in 0 until jsonArray.length()) {
                    val meal: JSONObject = jsonArray[i] as JSONObject // this is a json object
                    // add meal details to a list
                    tempMeal = Meal(
                        name = meal["strMeal"] as? String ?: null,
                        drinkAlternate = meal["strDrinkAlternate"] as? String ?: null,
                        category = meal["strCategory"] as? String ?: null,
                        area = meal["strArea"] as? String ?: null,
                        instructions = meal["strInstructions"] as? String ?: null,
                        mealThumb = meal["strMealThumb"] as? String ?: null,
                        tags = meal["strTags"] as? String ?: null,
                        youtube = meal["strYoutube"] as? String ?: null,
                        Ingredient1 = meal["strIngredient1"] as? String ?: null,
                        Ingredient2 = meal["strIngredient2"] as? String ?: null,
                        Ingredient3 = meal["strIngredient3"] as? String ?: null,
                        Ingredient4 = meal["strIngredient4"] as? String ?: null,
                        Ingredient5 = meal["strIngredient5"] as? String ?: null,
                        Ingredient6 = meal["strIngredient6"] as? String ?: null,
                        Ingredient7 = meal["strIngredient7"] as? String ?: null,
                        Ingredient8 = meal["strIngredient8"] as? String ?: null,
                        Ingredient9 = meal["strIngredient9"] as? String ?: null,
                        Ingredient10 = meal["strIngredient10"] as? String ?: null,
                        Ingredient11 = meal["strIngredient11"] as? String ?: null,
                        Ingredient12 = meal["strIngredient12"] as? String ?: null,
                        Ingredient13 = meal["strIngredient13"] as? String ?: null,
                        Ingredient14 = meal["strIngredient14"] as? String ?: null,
                        Ingredient15 = meal["strIngredient15"] as? String ?: null,
                        Ingredient16 = meal["strIngredient16"] as? String ?: null,
                        Ingredient17 = meal["strIngredient17"] as? String ?: null,
                        Ingredient18 = meal["strIngredient18"] as? String ?: null,
                        Ingredient19 = meal["strIngredient19"] as? String ?: null,
                        Ingredient20 = meal["strIngredient20"] as? String ?: null,
                        Measure1 = meal["strMeasure1"] as? String ?: null,
                        Measure2 = meal["strMeasure2"] as? String ?: null,
                        Measure3 = meal["strMeasure3"] as? String ?: null,
                        Measure4 = meal["strMeasure4"] as? String ?: null,
                        Measure5 = meal["strMeasure5"] as? String ?: null,
                        Measure6 = meal["strMeasure6"] as? String ?: null,
                        Measure7 = meal["strMeasure7"] as? String ?: null,
                        Measure8 = meal["strMeasure8"] as? String ?: null,
                        Measure9 = meal["strMeasure9"] as? String ?: null,
                        Measure10 = meal["strMeasure10"] as? String ?: null,
                        Measure11 = meal["strMeasure11"] as? String ?: null,
                        Measure12 = meal["strMeasure12"] as? String ?: null,
                        Measure13 = meal["strMeasure13"] as? String ?: null,
                        Measure14 = meal["strMeasure14"] as? String ?: null,
                        Measure15 = meal["strMeasure15"] as? String ?: null,
                        Measure16 = meal["strMeasure16"] as? String ?: null,
                        Measure17 = meal["strMeasure17"] as? String ?: null,
                        Measure18 = meal["strMeasure18"] as? String ?: null,
                        Measure19 = meal["strMeasure19"] as? String ?: null,
                        Measure20 = meal["strMeasure20"] as? String ?: null,
                        source = meal["strSource"] as? String ?: null,
                        imageSource = meal["strImageSource"] as? String ?: null,
                        creativeCommonsConfirmed = meal["strCreativeCommonsConfirmed"] as? String
                            ?: null,
                        dateModified = meal["dateModified"] as? String ?: null,
                    )
                    allMeal.add(tempMeal)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("allMeal", allMeal as ArrayList)
        outState.putBoolean("isAllsaved", isAllsaved_button)
    }

    //
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isAllsaved_button = savedInstanceState.getBoolean("isAllsaved")
        allMeal = savedInstanceState.getSerializable("allMeal") as ArrayList<Meal>

        save.isEnabled = isAllsaved_button
        createCard()
    }
}