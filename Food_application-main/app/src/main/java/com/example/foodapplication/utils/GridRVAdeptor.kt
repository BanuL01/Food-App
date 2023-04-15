package com.example.foodapplication.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.foodapplication.R

//source - https://www.geeksforgeeks.org/android-gridview-in-kotlin/

// on below line we are creating an
// adapter class for our grid view.
internal class GridRVAdeptor(
    // on below line we are creating two
    // variables for course list and context
    private val courseList: List<GridViewModal>,
    private val context: Context
) :
    BaseAdapter() {
    // in base adapter class we are creating variables
    // for layout inflater, course image view and course text view.
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
    // below method is use to return the count of course list
    override fun getCount(): Int {
        return courseList.size
    }

    // below function is use to return the item of grid view.
    override fun getItem(position: Int): Any? {
        return null
    }

    // below function is use to return item id of grid view.
    override fun getItemId(position: Int): Long {
        return 0
    }

    // in below function we are getting individual item of grid view.
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.meal_card, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
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

        var listofingredients = arrayListOf(courseList[position].meal.Ingredient1,courseList[position].meal.Ingredient2,courseList[position].meal.Ingredient3, courseList[position].meal.Ingredient4, courseList[position].meal.Ingredient5, courseList[position].meal.Ingredient6, courseList[position].meal.Ingredient7, courseList[position].meal.Ingredient8, courseList[position].meal.Ingredient9, courseList[position].meal.Ingredient10, courseList[position].meal.Ingredient11, courseList[position].meal.Ingredient12, courseList[position].meal.Ingredient13, courseList[position].meal.Ingredient14, courseList[position].meal.Ingredient15, courseList[position].meal.Ingredient16, courseList[position].meal.Ingredient17,courseList[position].meal.Ingredient18, courseList[position].meal.Ingredient19, courseList[position].meal.Ingredient20)
        var listofmeasures = arrayListOf(courseList[position].meal.Measure1, courseList[position].meal.Measure2, courseList[position].meal.Measure3, courseList[position].meal.Measure4, courseList[position].meal.Measure5, courseList[position].meal.Measure6, courseList[position].meal.Measure7, courseList[position].meal.Measure8, courseList[position].meal.Measure9, courseList[position].meal.Measure10, courseList[position].meal.Measure11, courseList[position].meal.Measure12, courseList[position].meal.Measure13, courseList[position].meal.Measure14, courseList[position].meal.Measure15, courseList[position].meal.Measure16, courseList[position].meal.Measure17, courseList[position].meal.Measure18, courseList[position].meal.Measure19, courseList[position].meal.Measure20)

        var Stringtofingredients = ""
        for (index in 0 until  20){
            if ( listofingredients[index] !="") Stringtofingredients += listofingredients[index] +" - " + listofmeasures[index]+",\n                       "
        }

//        // on below line we are setting image for our course image view.

        mealName.text = courseList[position].meal.name
        mealCategory.text = "Category: " + courseList[position].meal.category
        mealArea.text = "Area: " + courseList[position].meal.area
        mealInstruction.text = "Instructions: \n" + courseList[position].meal.instructions
        mealInstruction.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        mealTags.text = "Tags: " + courseList[position].meal.tags
        mealyt.text = "Youtube: " + courseList[position].meal.youtube
        mealIngredients.text = "Ingredients: " + Stringtofingredients
        mealsource.text = "Source: " + courseList[position].meal.source
        mealImageSrc.text = "Image Source: " + courseList[position].meal.imageSource
        mealConfirmed.text = "Creative Commons Confirmed: " + courseList[position].meal.creativeCommonsConfirmed
        mealDateModified.text = "Date Modified: " + courseList[position].meal.dateModified


//        mealArea.isVisible = false
//        mealInstruction.isVisible = false
//        mealTags.isVisible = false
//        mealyt.isVisible = false
//        mealIngredients.isVisible = false
//        mealsource.isVisible = false
//        mealDateModified.isVisible = false
//        mealConfirmed.isVisible = false
//        mealImageSrc.isVisible = false


        if (courseList[position].meal.source ==null){
            mealsource.isVisible = false
        }
        if (courseList[position].meal.tags ==null){
            mealTags.isVisible = false
        }
        if (courseList[position].meal.youtube ==null){
            mealyt.isVisible = false
        }
        if (courseList[position].meal.imageSource ==null){
            mealImageSrc.isVisible = false
        }
        if (courseList[position].meal.creativeCommonsConfirmed ==null){
            mealConfirmed.isVisible = false
        }
        if (courseList[position].meal.dateModified ==null){
            mealDateModified.isVisible = false
        }



        // Load image from URL into ImageView using Glide
        Glide.with(convertView)
            .load(courseList[position].meal.mealThumb)
            .into(mealImage)

//        // at last we are returning our convert view.
        return convertView!!
    }
}