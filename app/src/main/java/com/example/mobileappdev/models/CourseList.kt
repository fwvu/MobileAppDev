package com.example.mobileappdev.models

import android.os.Parcel
import android.os.Parcelable

data class CourseList(var dataCourseTitle:String, val dataCourseCode:String, val dataCourseInstructor:String, val dataCourseDescription:String, val dataCoursePrerequisites:String):
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dataCourseTitle)
        parcel.writeString(dataCourseCode)
        parcel.writeString(dataCourseInstructor)
        parcel.writeString(dataCourseDescription)
        parcel.writeString(dataCoursePrerequisites)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseList> {
        override fun createFromParcel(parcel: Parcel): CourseList {
            return CourseList(parcel)
        }

        override fun newArray(size: Int): Array<CourseList?> {
            return arrayOfNulls(size)
        }
    }
}