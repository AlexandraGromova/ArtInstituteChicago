package com.example.artinstitutechicago.data.model

//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Artwork(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("artist_title")
    val artist_title: String,
    @SerializedName("image_id")
    val image_id: String,
    @SerializedName("department_title")
    val department_title: String,
    @SerializedName("date_display")
    val date_display: String,
    @SerializedName("medium_display")
    val medium_display: String,
    @SerializedName("dimensions")
    val dimensions: String
)
