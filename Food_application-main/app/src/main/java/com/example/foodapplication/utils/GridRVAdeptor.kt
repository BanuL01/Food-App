package com.example.foodapplication.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
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
    private lateinit var mealThumb: TextView
    private lateinit var mealTags: TextView
    private lateinit var  mealyt: TextView
    private lateinit var mealIngredients: TextView
    private lateinit var mealIngredients2: TextView
    private lateinit var minicard_image: LinearLayout

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
        mealThumb = convertView.findViewById(R.id.meal_tv)
        mealTags = convertView.findViewById(R.id.tags_tv)
        mealyt = convertView.findViewById(R.id.yt_tv)
        mealIngredients = convertView.findViewById(R.id.ingre_tv)
        mealIngredients2 = convertView.findViewById(R.id.ingre_tv)




//        // on below line we are setting image for our course image view.

        mealName.text = courseList[position].meal.name
        mealCategory.text = "Category: " + courseList[position].meal.category
        mealArea.text = "Area: " + courseList[position].meal.area
        mealInstruction.text = "Instructions: " + courseList[position].meal.instructions
        mealThumb.text = "Meal Thumb: " + courseList[position].meal.mealThumb
        mealTags.text = "Tags: " + courseList[position].meal.tags
        mealyt.text = "Youtube: " + courseList[position].meal.youtube
        mealIngredients.text = "Ingredients: " + courseList[position].meal.Ingredient1
        mealIngredients2.text = "Ingredients: " + courseList[position].meal.Ingredient2


//        // on below line we are setting text in our course text view.
//        minicard_name.setText(courseList[position].meal_name)
//        minicard_category.setText(courseList[position].meal_category)
//        var bitmapDrawable: BitmapDrawable? = null
//        Glide.with(convertView)
//            .asBitmap()
//            .load(courseList[position].meal_thumbnail)
//            .into(object : CustomTarget<Bitmap>() {
//                override fun onResourceReady(
//                    resource: Bitmap,
//                    transition: Transition<in Bitmap>?
//                ) {
//                    val drawable = BitmapDrawable(context.resources, resource)
//                    drawable.alpha = 150
//                    minicard_image.background = drawable
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//                    // this is called when imageView is cleared on lifecycle call or for
//                    // some other reason.
//                    // if you are referencing the bitmap somewhere else too other than this imageView
//                    // clear it here as you can no longer have the bitmap
//                }
//            })
//        // at last we are returning our convert view.
        return convertView!!
    }
}