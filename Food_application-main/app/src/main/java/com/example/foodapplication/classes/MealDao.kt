package com.example.foodapplication.classes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MealDao {
    @Query("Select * from meals")
    suspend fun getAll(): List<Meal>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(vararg user: Meal)
    @Insert
    suspend fun insertAll(vararg users: Meal)
}