package com.manhal.newsapp.data.network

import com.manhal.newsapp.data.dto.Articles
import com.manhal.newsapp.data.dto.NewsResult
import kotlinx.coroutines.flow.Flow

interface RemoteData {
    suspend fun getTopArticles(section: String): Flow<NewsResult<Articles>>
}