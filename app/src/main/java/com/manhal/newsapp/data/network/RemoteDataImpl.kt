package com.manhal.newsapp.data.network

import android.util.Log
import com.manhal.newsapp.API_KEY
import com.manhal.newsapp.data.dto.Articles
import com.manhal.newsapp.data.dto.NewsResult
import com.manhal.newsapp.data.error.NETWORK_ERROR
import com.manhal.newsapp.data.network.ArticleApi
import com.manhal.newsapp.data.network.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataImpl @Inject constructor(private val articleApi: ArticleApi) : RemoteData {

    override suspend fun getTopArticles(section: String): Flow<NewsResult<Articles>> =
        flow {
            try {
                val response = articleApi.getTopArticles(API_KEY, section)

                if (response.isSuccessful) {
                    emit(NewsResult.Success(response.body()!!))

                }
            } catch (e: Exception) {
                Log.d("Error", "getTopArticles: ${e.message}")
                emit(NewsResult.DataError<Articles>(errorCode = NETWORK_ERROR, message = e.message))
            }
        }

}