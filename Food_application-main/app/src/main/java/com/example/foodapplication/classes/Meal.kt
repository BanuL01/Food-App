package com.example.foodapplication.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "drinkAlternate") var drinkAlternate: String? = null,
    @ColumnInfo(name = "category") var category: String? = null,
    @ColumnInfo(name = "area") var area: String? = null,
    @ColumnInfo(name = "instructions") var instructions: String? = null,
    @ColumnInfo(name = "mealThumb") var mealThumb: String? = null,
    @ColumnInfo(name = "tags") var tags: String? = null,
    @ColumnInfo(name = "youtube") var youtube: String? = null,
    @ColumnInfo(name = "source") var source: String? = null,
    @ColumnInfo(name = "imageSource") var imageSource: String? = null,
    @ColumnInfo(name = "creativeCommonsConfirmed") var creativeCommonsConfirmed: String? = null,
    @ColumnInfo(name = "dateModified") var dateModified: String? = null,
    @ColumnInfo(name = "Ingredient1") var Ingredient1: String? = null,
    @ColumnInfo(name = "Ingredient2") var Ingredient2: String? = null,
    @ColumnInfo(name = "Ingredient3") var Ingredient3: String? = null,
    @ColumnInfo(name = "Ingredient4") var Ingredient4: String? = null,
    @ColumnInfo(name = "Ingredient5") var Ingredient5: String? = null,
    @ColumnInfo(name = "Ingredient6") var Ingredient6: String? = null,
    @ColumnInfo(name = "Ingredient7") var Ingredient7: String? = null,
    @ColumnInfo(name = "Ingredient8") var Ingredient8: String? = null,
    @ColumnInfo(name = "Ingredient9") var Ingredient9: String? = null,
    @ColumnInfo(name = "Ingredient10") var Ingredient10: String? = null,
    @ColumnInfo(name = "Ingredient11") var Ingredient11: String? = null,
    @ColumnInfo(name = "Ingredient12") var Ingredient12: String? = null,
    @ColumnInfo(name = "Ingredient13") var Ingredient13: String? = null,
    @ColumnInfo(name = "Ingredient14") var Ingredient14: String? = null,
    @ColumnInfo(name = "Ingredient15") var Ingredient15: String? = null,
    @ColumnInfo(name = "Ingredient16") var Ingredient16: String? = null,
    @ColumnInfo(name = "Ingredient17") var Ingredient17: String? = null,
    @ColumnInfo(name = "Ingredient18") var Ingredient18: String? = null,
    @ColumnInfo(name = "Ingredient19") var Ingredient19: String? = null,
    @ColumnInfo(name = "Ingredient20") var Ingredient20: String? = null,
    @ColumnInfo(name = "Measure1") var Measure1: String? = null,
    @ColumnInfo(name = "Measure2") var Measure2: String? = null,
    @ColumnInfo(name = "Measure3") var Measure3: String? = null,
    @ColumnInfo(name = "Measure4") var Measure4: String? = null,
    @ColumnInfo(name = "Measure5") var Measure5: String? = null,
    @ColumnInfo(name = "Measure6") var Measure6: String? = null,
    @ColumnInfo(name = "Measure7") var Measure7: String? = null,
    @ColumnInfo(name = "Measure8") var Measure8: String? = null,
    @ColumnInfo(name = "Measure9") var Measure9: String? = null,
    @ColumnInfo(name = "Measure10") var Measure10: String? = null,
    @ColumnInfo(name = "Measure11") var Measure11: String? = null,
    @ColumnInfo(name = "Measure12") var Measure12: String? = null,
    @ColumnInfo(name = "Measure13") var Measure13: String? = null,
    @ColumnInfo(name = "Measure14") var Measure14: String? = null,
    @ColumnInfo(name = "Measure15") var Measure15: String? = null,
    @ColumnInfo(name = "Measure16") var Measure16: String? = null,
    @ColumnInfo(name = "Measure17") var Measure17: String? = null,
    @ColumnInfo(name = "Measure18") var Measure18: String? = null,
    @ColumnInfo(name = "Measure19") var Measure19: String? = null,
    @ColumnInfo(name = "Measure20") var Measure20: String? = null,



    )