package com.example.foodapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import com.example.foodapplication.classes.Meal
import com.example.foodapplication.utils.GridRVAdeptor
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

    var allMeal = arrayListOf<Meal>()
    override fun onCreate(savedInstanceState: Bundle?) {
        // on below line we are creating
        // variables for grid view and course list
        lateinit var courseGRV: GridView
        lateinit var courseList: List<GridViewModal>

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_by_ingredients)

        var meal_id = findViewById<EditText>(R.id.ingredient_et)

        var retrieve = findViewById<Button>(R.id.retrieve_button)
        retrieve.setOnClickListener {
            //collecting all JSON string
            var stb = StringBuilder()
            //changing the URl
            val url_string = "https://www.themealdb.com/api/json/v1/1/filter.php?i=" + meal_id.text.toString()
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
            // initializing variables of grid view with their ids.
            courseGRV = findViewById(R.id.grid_layout1)
            courseList = ArrayList<GridViewModal>()

            // on below line we are adding data to
            // our course list with image and course name.
            for (meal in allMeal){
                courseList = courseList + GridViewModal(meal)
            }



            // on below line we are initializing our course adapter
            // and passing course list and context.
            val courseAdapter = GridRVAdeptor(courseList = courseList, this)

            // on below line we are setting adapter to our grid view.
            courseGRV.adapter = courseAdapter
        }



        // on below line we are adding on item
        // click listener for our grid view.
//        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
//            // inside on click method we are simply displaying
//            // a toast message with course name.
//            Toast.makeText(
//                applicationContext, courseList[position].courseName + " selected",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
    }

    suspend fun parseJSON(stb: java.lang.StringBuilder) {
// this contains the full JSON returned by the Web Service
        val json = JSONObject(stb.toString())
// Information about all the meal extracted by this function
        allMeal.clear() //to reset the array when refreshing the next time
        var jsonArray: JSONArray = json.getJSONArray("meals")
// extract all the meals from the JSON array
        for (i in 0 until jsonArray.length()) {
            val meal: JSONObject = jsonArray[i] as JSONObject // this is a json object

            //collecting all JSON string
            val stb = StringBuilder()
            //changing the URl
            val url_string = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + meal["idMeal"]
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
                    name = meal["strMeal"] as? String?: null,
                    drinkAlternate =  meal["strDrinkAlternate"] as? String?: null,
                    category = meal["strCategory"] as? String?: null,
                    area = meal["strArea"] as? String?: null,
                    instructions = meal["strInstructions"] as? String?: null,
                    mealThumb =  meal["strMealThumb"] as? String?: null,
                    tags =  meal["strTags"] as? String?: null,
                    youtube = meal["strYoutube"] as? String?: null,
                    Ingredient1 = meal["strIngredient1"] as? String?: null,
                    Ingredient2 = meal["strIngredient2"] as? String?: null,
                    Ingredient3 = meal["strIngredient3"] as? String?: null,
                    Ingredient4 =meal["strIngredient4"] as? String?: null,
                    Ingredient5 = meal["strIngredient5"] as? String?: null,
                    Ingredient6 = meal["strIngredient6"] as? String?: null,
                    Ingredient7 = meal["strIngredient7"] as? String?: null,
                    Ingredient8 = meal["strIngredient8"] as? String?: null,
                    Ingredient9 = meal["strIngredient9"] as? String?: null,
                    Ingredient10 = meal["strIngredient10"] as? String?: null,
                    Ingredient11 = meal["strIngredient11"] as? String?: null,
                    Ingredient12 = meal["strIngredient12"] as? String?: null,
                    Ingredient13 = meal["strIngredient13"] as? String?: null,
                    Ingredient14 =meal["strIngredient14"] as? String?: null,
                    Ingredient15 = meal["strIngredient15"] as? String?: null,
                    Ingredient16 = meal["strIngredient16"] as? String?: null,
                    Ingredient17 = meal["strIngredient17"] as? String?: null,
                    Ingredient18 = meal["strIngredient18"] as? String?: null,
                    Ingredient19 = meal["strIngredient19"] as? String?: null,
                    Ingredient20 = meal["strIngredient20"] as? String?: null,
                    Measure1 =  meal["strMeasure1"] as? String?: null,
                    Measure2 =  meal["strMeasure2"] as? String?: null,
                    Measure3 = meal["strMeasure3"] as? String?: null,
                    Measure4 =  meal["strMeasure4"] as? String?: null,
                    Measure5 =  meal["strMeasure5"] as? String?: null,
                    Measure6 =  meal["strMeasure6"] as? String?: null,
                    Measure7 = meal["strMeasure7"] as? String?: null,
                    Measure8 =  meal["strMeasure8"] as? String?: null,
                    Measure9 =  meal["strMeasure9"] as? String?: null,
                    Measure10 =  meal["strMeasure10"] as? String?: null,
                    Measure11 =  meal["strMeasure11"] as? String?: null,
                    Measure12 =  meal["strMeasure12"] as? String?: null,
                    Measure13 = meal["strMeasure13"] as? String?: null,
                    Measure14 =  meal["strMeasure14"] as? String?: null,
                    Measure15 =  meal["strMeasure15"] as? String?: null,
                    Measure16 =  meal["strMeasure16"] as? String?: null,
                    Measure17 = meal["strMeasure17"] as? String?: null,
                    Measure18 =  meal["strMeasure18"] as? String?: null,
                    Measure19 =  meal["strMeasure19"] as? String?: null,
                    Measure20 =  meal["strMeasure20"] as? String?: null,
                    source =  meal["strSource"] as? String?: null,
                    imageSource = meal["strImageSource"] as? String?: null,
                    creativeCommonsConfirmed = meal["strCreativeCommonsConfirmed"] as? String?: null,
                    dateModified =  meal["dateModified"] as? String?: null,
                )
                allMeal.add(tempMeal)
             }
        }
    }
}