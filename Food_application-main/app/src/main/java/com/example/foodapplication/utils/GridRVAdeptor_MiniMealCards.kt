package com.example.foodapplication.utils

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.foodapplication.R

//source - https://www.geeksforgeeks.org/android-gridview-in-kotlin/

//creating an adapter class for our grid view.
internal class GridRVAdeptor_MiniMealCards(
    //creating two variables for course list and context
    private val mealGridList: List<GridViewModal>,
    private val context: Context
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var mealName: TextView
    private lateinit var mealCategory: TextView
    private lateinit var mealImage: ImageView
    // returns the count of mealGrid  list
    override fun getCount(): Int {
        return mealGridList.size
    }

    // returns the item of grid view.
    override fun getItem(position: Int): Any? {
        return null
    }

    //returns item id of grid view.
    override fun getItemId(position: Int): Long {
        return 0
    }

    //getting individual item of grid view.
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        // checking if layout inflater is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        //checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // passing the layout file which  have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.mini_card, null)
        }
        // initializing  image view and  text view with their ids.
        mealName = convertView!!.findViewById(R.id.name_id)
        mealCategory = convertView.findViewById(R.id.category_tv)
        mealImage = convertView.findViewById(R.id.mealthumbId)

//        //setting image for our  image view.

        mealName.text = mealGridList[position].meal.name
        mealCategory.text = "Category: " + mealGridList[position].meal.category

        // Load image from URL into ImageView using Glide
        Glide.with(convertView)
            .load(mealGridList[position].meal.mealThumb)
            .into(mealImage)

        return convertView
    }
}