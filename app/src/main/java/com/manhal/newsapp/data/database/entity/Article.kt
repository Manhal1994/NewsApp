package com.manhal.newsapp.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
@Entity(tableName = "article")

data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @Json(name = "abstract")
    var descrption: String?,
    @Json(name = "title")
    var title: String?,
) : Parcelable {
    constructor() : this(0, "", "")
}