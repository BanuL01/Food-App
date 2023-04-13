package com.example.foodapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        // on below line we are creating
        // variables for grid view and course list
        lateinit var courseGRV: GridView
        lateinit var courseList: List<GridViewModal>

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_by_ingredients)


        // collecting all the JSON string
        var stb = StringBuilder()
        val url_string = "https://www.themealdb.com/api/json/v1/1/search.php?s=ëgg"
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
                    parseJSON(stb)
                }
            }
        }

        // initializing variables of grid view with their ids.
        courseGRV = findViewById(R.id.grid_layout1)
        courseList = ArrayList<GridViewModal>()

        // on below line we are adding data to
        // our course list with image and course name.
        courseList = courseList + GridViewModal(Meal())
        courseList = courseList + GridViewModal(Meal())
        courseList = courseList + GridViewModal(Meal())
        courseList = courseList + GridViewModal(Meal())
        courseList = courseList + GridViewModal(Meal())

        // on below line we are initializing our course adapter
        // and passing course list and context.
        val courseAdapter = GridRVAdeptor(courseList = courseList, this)

        // on below line we are setting adapter to our grid view.
        courseGRV.adapter = courseAdapter

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
        var allMeal = arrayListOf<Meal>()
        var jsonArray: JSONArray = json.getJSONArray("meals")
// extract all the books from the JSON array
        for (i in 0 until jsonArray.length()) {
            val meal: JSONObject = jsonArray[i] as JSONObject // this is a json object
// extract the title

            val temp_meal =Meal(
                name = meal["strMeal"] as String,
                drinkAlternate = meal["strDrinkAlternate"] as String,
            )

            allMeal.add(meal_name)
        }
        println(allMeal)
    }
}