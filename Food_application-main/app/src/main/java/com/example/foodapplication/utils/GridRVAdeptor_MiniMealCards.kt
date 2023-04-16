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

// on below line we are creating an
// adapter class for our grid view.
internal class GridRVAdeptor_MiniMealCards(
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
            convertView = layoutInflater!!.inflate(R.layout.mini_card, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
        mealName = convertView!!.findViewById(R.id.name_id)
        mealCategory = convertView.findViewById(R.id.category_tv)
        mealImage = convertView.findViewById(R.id.mealthumbId)

//        // on below line we are setting image for our course image view.

        mealName.text = courseList[position].meal.name
        mealCategory.text = "Category: " + courseList[position].meal.category

        // Load image from URL into ImageView using Glide
        Glide.with(convertView)
            .load(courseList[position].meal.mealThumb)
            .into(mealImage)

//        // at last we are returning our convert view.
        return convertView
    }
}