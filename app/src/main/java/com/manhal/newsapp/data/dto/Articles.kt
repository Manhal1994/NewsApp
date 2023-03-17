package com.manhal.newsapp.data.dto


import android.os.Parcelable
import com.manhal.newsapp.data.database.entity.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class Articles(
    @Json(name = "copyright")
    val copyright: String?,
    @Json(name = "last_updated")
    val lastUpdated: String?,
    @Json(name = "num_results")
    val numResults: Int?,
    @Json(name = "results")
    val articles: List<Article>?,
    @Json(name = "section")
    val section: String?,
    @Json(name = "status")
    val status: String?
) : Parcelable