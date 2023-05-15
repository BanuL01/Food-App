package com.example.foodapplication.utils

import android.content.Context
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.foodapplication.R

//source - https://www.geeksforgeeks.org/android-gridview-in-kotlin/

// creating an adapter class for our grid view.
internal class GridRVAdeptor_MealCards(

    // creating two variables for grid list and context
    private val grid_List: List<GridViewModal>,
    private val context: Context
) :
    BaseAdapter() {
    //creating variable for layout inflater,  image view and  text view.
    private var layoutInflater: LayoutInflater? = null
    private lateinit var mealName: TextView
    private lateinit var mealCategory: TextView
    private lateinit var mealArea: TextView
    private lateinit var mealInstruction: TextView
    private lateinit var mealTags: TextView
    private lateinit var  mealyt: TextView
    private lateinit var mealIngredients: TextView
    private lateinit var mealsource :TextView
    private lateinit var mealImageSrc :TextView
    private lateinit var mealConfirmed :TextView
    private lateinit var mealDateModified :TextView
    private lateinit var mealImage: ImageView

    //  return the count of grid list
    override fun getCount(): Int {
        return grid_List.size
    }

    // return the item of grid view.
    override fun getItem(position: Int): Any? {
        return null
    }

    // return item id of grid view.
    override fun getItemId(position: Int): Long {
        return 0
    }

    // getting individual item of grid view.
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        // checking if layout inflater is null, if it is null, initializing it.
        if (layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // checking if convert view is null, If it is null, initializing it.
        if (convertView == null) {
            // pass the layout file which  have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.meal_card, null)
        }
        // initialize  grid image view and grid text view with their ids.
        mealName = convertView!!.findViewById(R.id.name_id)
        mealCategory = convertView.findViewById(R.id.category_tv)
        mealArea = convertView.findViewById(R.id.area_tv)
        mealInstruction = convertView.findViewById(R.id.instr_tv)
        mealTags = convertView.findViewById(R.id.tags_tv)
        mealyt = convertView.findViewById(R.id.yt_tv)
        mealIngredients = convertView.findViewById(R.id.ingre_tv)
        mealsource = convertView.findViewById(R.id.src_tv)
        mealImageSrc = convertView.findViewById(R.id.img_src_tv)
        mealConfirmed = convertView.findViewById(R.id.ccc_tv)
        mealDateModified = convertView.findViewById(R.id.dm_tv)
        mealImage = convertView.findViewById(R.id.mealthumbId)
        var card = convertView.findViewById<CardView>(R.id.cv1_id)

        var ingredientsList = arrayListOf(grid_List[position].meal.Ingredient1,grid_List[position].meal.Ingredient2,grid_List[position].meal.Ingredient3, grid_List[position].meal.Ingredient4, grid_List[position].meal.Ingredient5, grid_List[position].meal.Ingredient6, grid_List[position].meal.Ingredient7, grid_List[position].meal.Ingredient8, grid_List[position].meal.Ingredient9, grid_List[position].meal.Ingredient10, grid_List[position].meal.Ingredient11, grid_List[position].meal.Ingredient12, grid_List[position].meal.Ingredient13, grid_List[position].meal.Ingredient14, grid_List[position].meal.Ingredient15, grid_List[position].meal.Ingredient16, grid_List[position].meal.Ingredient17,grid_List[position].meal.Ingredient18, grid_List[position].meal.Ingredient19, grid_List[position].meal.Ingredient20)
        var measuresList = arrayListOf(grid_List[position].meal.Measure1, grid_List[position].meal.Measure2, grid_List[position].meal.Measure3, grid_List[position].meal.Measure4, grid_List[position].meal.Measure5, grid_List[position].meal.Measure6, grid_List[position].meal.Measure7, grid_List[position].meal.Measure8, grid_List[position].meal.Measure9, grid_List[position].meal.Measure10, grid_List[position].meal.Measure11, grid_List[position].meal.Measure12, grid_List[position].meal.Measure13, grid_List[position].meal.Measure14, grid_List[position].meal.Measure15, grid_List[position].meal.Measure16, grid_List[position].meal.Measure17, grid_List[position].meal.Measure18, grid_List[position].meal.Measure19, grid_List[position].meal.Measure20)

        var Stringtofingredients = ""
        for (index in 0 until  20){
            if ( ingredientsList[index] !="") Stringtofingredients += ingredientsList[index] +" - " + measuresList[index]+",\n                       "
        }

       //setting image for grid image view.

        mealName.text = grid_List[position].meal.name
        mealCategory.text = "Category: " + grid_List[position].meal.category
        mealArea.text = "Area: " + grid_List[position].meal.area
        mealInstruction.text = "Instructions: \n" + grid_List[position].meal.instructions
        mealInstruction.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        mealTags.text = "Tags: " + grid_List[position].meal.tags
        mealyt.text = "Youtube: " + grid_List[position].meal.youtube
        mealIngredients.text = "Ingredients: " + Stringtofingredients
        mealsource.text = "Source: " + grid_List[position].meal.source
        mealImageSrc.text = "Image Source: " + grid_List[position].meal.imageSource
        mealConfirmed.text = "Creative Commons Confirmed: " + grid_List[position].meal.creativeCommonsConfirmed
        mealDateModified.text = "Date Modified: " + grid_List[position].meal.dateModified


        if (grid_List[position].meal.source ==null){
            mealsource.isVisible = false
        }
        if (grid_List[position].meal.tags ==null){
            mealTags.isVisible = false
        }
        if (grid_List[position].meal.youtube ==null){
            mealyt.isVisible = false
        }
        if (grid_List[position].meal.imageSource ==null){
            mealImageSrc.isVisible = false
        }
        if (grid_List[position].meal.creativeCommonsConfirmed ==null){
            mealConfirmed.isVisible = false
        }
        if (grid_List[position].meal.dateModified ==null){
            mealDateModified.isVisible = false
        }


        // Load image from URL into ImageView using Glide
        Glide.with(convertView)
            .load(grid_List[position].meal.mealThumb)
            .into(mealImage)

        return convertView!!
    }
}