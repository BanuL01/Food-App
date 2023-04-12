package com.example.foodapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room
import com.example.foodapplication.classes.AppDatabase
import com.example.foodapplication.classes.Meal
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class options : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        // create the database
        val db = Room.databaseBuilder(this, AppDatabase::class.java,
            "mydatabase").build()
        val MealDao = db.MealDao()

        var add_meal_db = findViewById<Button>(R.id.add_meals)
        add_meal_db.setOnClickListener {
            println("reached")
            runBlocking {
                launch {
                    val sweet_and_sour_pork = Meal(
                        name = "Sweet and Sour Pork",
                        category = "Pork",
                        area = "Chinese",
                        instructions = "Preparation\\r\\n1. Crack the egg into a bowl. Separate the egg white and yolk.\\r\\n\\r\\nSweet and Sour Pork\\r\\n2. Slice the pork tenderloin into ips.\\r\\n\\r\\n3. Prepare the marinade using a pinch of salt, one teaspoon of starch, two teaspoons of light soy sauce, and an egg white.\\r\\n\\r\\n4. Marinade the pork ips for about 20 minutes.\\r\\n\\r\\n5. Put the remaining starch in a bowl. Add some water and vinegar to make a starchy sauce.\\r\\n\\r\\nSweet and Sour Pork\\r\\nCooking Inuctions\\r\\n1. Pour the cooking oil into a wok and heat to 190\\u00b0C (375\\u00b0F). Add the marinated pork ips and fry them until they turn brown. Remove the cooked pork from the wok and place on a plate.\\r\\n\\r\\n2. Leave some oil in the wok. Put the tomato sauce and white sugar into the wok, and heat until the oil and sauce are fully combined.\\r\\n\\r\\n3. Add some water to the wok and thoroughly heat the sweet and sour sauce before adding the pork ips to it.\\r\\n\\r\\n4. Pour in the starchy sauce. Stir-fry all the ingredients until the pork and sauce are thoroughly mixed together.\\r\\n\\r\\n5. Serve on a plate and add some coriander for decoration.",
                        mealThumb = "https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/1529442316.jpg",
                        tags = "Sweet",
                        youtube = "https://www.youtube.com/watch?v=mdaBIhgEAMo",
                        Ingredient1 = "Pork",
                        Ingredient2 = "Egg",
                        Ingredient3 = "Water",
                        Ingredient4 = "Salt",
                        Ingredient5 = "Salt",
                        Ingredient6 = "Soy Sauce",
                        Ingredient7 = "Starch",
                        Ingredient8 = "Tomato Puree",
                        Ingredient9 = "Vinegar",
                        Ingredient10 = "Coriander",
                        Measure1 = "200g",
                        Measure2 = "1",
                        Measure3 = "Dash",
                        Measure4 = "1/2 tsp",
                        Measure5 = "1 tsp ",
                        Measure6 = "10g",
                        Measure7 = "10g",
                        Measure8 = "30g",
                        Measure9 = "10g",
                        Measure10 = "Dash"
                    )

                    val chicken_marengo = Meal(
                        name = "Chicken Marengo",
                        category = " Chicken",
                        area = "French",
                        instructions = "Heat the oil in a large flameproof casserole dish and stir-fry the mushrooms until they start to soften. Add the chicken legs and cook briefly on each side to colour them a little.\\r\\nPour in the passata, crumble in the stock cube and stir in the olives. Season with black pepper \\u2013 you shouldn\\u2019t need salt. Cover and simmer for 40 mins until the chicken is tender. Sprinkle with parsley and serve with pasta and a salad, or mash and green veg, if you like.",
                        mealThumb = "\"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/qpxvuq1511798906.jpg",
                        youtube =  "\\/\\/www.youtube.com\\/watch?v=U33HYUr-0Fw",
                        Ingredient1 = "Olive Oil",
                        Ingredient2 = "Mushrooms",
                        Ingredient3 = "Chicken Legs",
                        Ingredient4 = "Passata",
                        Ingredient5 = "Chicken Stock Cube",
                        Ingredient6 = "Black Olives",
                        Ingredient7 = "Parsley",
                        Measure1 = "1 tbs",
                        Measure2 = "300g",
                        Measure3 = "4",
                        Measure4 = "500g",
                        Measure5 = "1",
                        Measure6 = "100g ",
                        Measure7 = "Chopped",
                        source = "https://www.bbcgoodfood.com/recipes/3146682/chicken-marengo",

                    )


                    MealDao.insertMeal(sweet_and_sour_pork, chicken_marengo)
                    val meals: List<Meal> = MealDao.getAll()
                    print(meals)
                }
            }
        }


    }
}