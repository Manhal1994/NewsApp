package com.manhal.newsapp.data.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class Multimedia(
    @Json(name = "caption")
    val caption: String?,
    @Json(name = "copyright")
    val copyright: String?,
    @Json(name = "format")
    val format: String?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "subtype")
    val subtype: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "width")
    val width: Int?
) : Parcelable