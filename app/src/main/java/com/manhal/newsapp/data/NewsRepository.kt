package com.manhal.newsapp.data

import com.manhal.newsapp.data.database.entity.Article
import com.manhal.newsapp.data.dto.Articles
import com.manhal.newsapp.data.dto.NewsResult
import kotlinx.coroutines.flow.*


interface NewsRepository {

    suspend fun getTopArticles(section: String): Flow<NewsResult<List<Article>>>

}