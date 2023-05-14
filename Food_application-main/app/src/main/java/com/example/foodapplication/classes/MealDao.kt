package com.example.foodapplication.classes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MealDao {
    //queries and returns all rows from the "meals" table in a suspended List of Meal objects.
    @Query("Select * from meals")
    suspend fun getAll(): List<Meal>
    //inserts a Meal object into the database, replacing any existing row with the same primary key if there is a conflict.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(vararg user: Meal)
    // inserts all the specified meals into the database.
    @Insert
    suspend fun insertAll(vararg users: Meal)
}