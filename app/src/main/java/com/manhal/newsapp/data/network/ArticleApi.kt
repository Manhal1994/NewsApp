package com.manhal.newsapp.data.network

import com.manhal.newsapp.data.dto.Articles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleApi {
    @GET("v2/{section}")
    suspend fun getTopArticles(
        @Path("section") section: String,
        @Query("api-key") apiKey: String
    ): Response<Articles>
}
