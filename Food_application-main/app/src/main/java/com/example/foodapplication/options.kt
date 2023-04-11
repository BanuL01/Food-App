package com.example.foodapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room
import com.example.foodapplication.classes.AppDatabase
import com.example.foodapplication.classes.Meal
import com.example.foodapplication.classes.MealDao
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
            runBlocking {
                launch {
                    val sweet_and_sour_pork = User(1, "John", "Smith")
                    MealDao.insertUsers(user, user2, user3)
//                    val users: List<Meal> = MealDao.getAll()
//                    for (u in users) {
//                        tv.append("\n ${u.firstName} ${u.lastName}")
//                    }
                }
            }
        }


    }
}