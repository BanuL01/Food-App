package com.example.foodapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
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

        var search_ingredients = findViewById<Button>(R.id.search2)
        search_ingredients.setOnClickListener {
            val intent = Intent(this, Search_by_ingredients::class.java)
            startActivity(intent)
        }

        var add_meal_db = findViewById<Button>(R.id.add_meals)
        add_meal_db.setOnClickListener {
            println("reached") //test

            //show the toast
            fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
                Toast.makeText(context, message, duration).show()
            }

            showToast(this, "Meal added to the DataBase!")

            //meal adding to tables
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

                    val beef_Banh_Mi_Bowls = Meal(
                        name = "Beef Banh Mi Bowls with Sriracha Mayo, Carrot & Pickled Cucumber",
                        category = "Beef",
                        area = "Vietnamese",
                        instructions = "Add'l ingredients: mayonnaise, siracha\r\n\r\n1\r\n\r\nPlace rice in a fine-mesh sieve and rinse until water runs clear. Add to a small pot with 1 cup water (2 cups for 4 servings) and a pinch of salt. Bring to a boil, then cover and reduce heat to low. Cook until rice is tender, 15 minutes. Keep covered off heat for at least 10 minutes or until ready to serve.\r\n\r\n2\r\n\r\nMeanwhile, wash and dry all produce. Peel and finely chop garlic. Zest and quarter lime (for 4 servings, zest 1 lime and quarter both). Trim and halve cucumber lengthwise; thinly slice crosswise into half-moons. Halve, peel, and medium dice onion. Trim, peel, and grate carrot.\r\n\r\n3\r\n\r\nIn a medium bowl, combine cucumber, juice from half the lime, \u00bc tsp sugar (\u00bd tsp for 4 servings), and a pinch of salt. In a small bowl, combine mayonnaise, a pinch of garlic, a squeeze of lime juice, and as much sriracha as you\u2019d like. Season with salt and pepper.\r\n\r\n4\r\n\r\nHeat a drizzle of oil in a large pan over medium-high heat. Add onion and cook, stirring, until softened, 4-5 minutes. Add beef, remaining garlic, and 2 tsp sugar (4 tsp for 4 servings). Cook, breaking up meat into pieces, until beef is browned and cooked through, 4-5 minutes. Stir in soy sauce. Turn off heat; taste and season with salt and pepper.\r\n\r\n5\r\n\r\nFluff rice with a fork; stir in lime zest and 1 TBSP butter. Divide rice between bowls. Arrange beef, grated carrot, and pickled cucumber on top. Top with a squeeze of lime juice. Drizzle with sriracha mayo.",
                        mealThumb = "https://www.themealdb.com/images/media/meals/z0ageb1583189517.jpg",
                        Ingredient1 = "Rice",
                        Ingredient2 = "Onion",
                        Ingredient3 = "Lime",
                        Ingredient4 = "Garlic Clove",
                        Ingredient5 = "Cucumber",
                        Ingredient6 = "Carrots",
                        Ingredient7 = "Ground Beef",
                        Ingredient8 = "Soy Sauce",
                        Measure1 = "White",
                        Measure2 = "1",
                        Measure3 = "1",
                        Measure4 = "3",
                        Measure5 = "1",
                        Measure6 = "3 oz ",
                        Measure7 = "1 lb",
                        Measure8 = "2 oz ",
                        source = "",
                    )



                    MealDao.insertMeal(sweet_and_sour_pork, chicken_marengo)
                    val meals: List<Meal> = MealDao.getAll()
                    print(meals)


                }

            }
        }


    }
}