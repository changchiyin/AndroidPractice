package com.example.practice.Model

import com.google.gson.annotations.SerializedName

data class BGDataClass(
    @SerializedName("F_Name_Ch")
    val name:String,
    @SerializedName("F_Location")
    val location:String,
    @SerializedName("F_Feature")
    val feature:String,
    @SerializedName("F_Pic01_URL")
    val url:String
)
