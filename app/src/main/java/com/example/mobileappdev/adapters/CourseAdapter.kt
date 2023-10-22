package com.example.mobileappdev.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdev.R
import com.example.mobileappdev.models.CourseList

class CourseAdapter (private val courseList: ArrayList<CourseList>): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>(){

    var onItemClick: ((CourseList) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.course_item_layout, parent, false)
        return CourseViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {

        val currentItem = courseList[position]
        holder.rvTitle.text = currentItem.dataCourseTitle

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
    }

    class CourseViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val rvTitle:TextView = itemView.findViewById(R.id.courseTitle)

    }
}