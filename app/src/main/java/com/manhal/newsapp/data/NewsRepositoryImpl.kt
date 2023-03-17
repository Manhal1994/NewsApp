package com.manhal.newsapp.data

import com.manhal.newsapp.API_KEY
import com.manhal.newsapp.data.database.ArticleDao
import com.manhal.newsapp.data.dto.NewsResult
import com.manhal.newsapp.data.database.ArticleDatabase
import com.manhal.newsapp.data.database.entity.Article
import com.manhal.newsapp.data.network.ArticleApi
import com.manhal.newsapp.utils.Network
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val articleApi: ArticleApi,
    private val articleDao: ArticleDao,
    private val network: Network
) : NewsRepository {

    override suspend fun getTopArticles(section: String): Flow<NewsResult<List<Article>>> = flow {
        emit(NewsResult.Loading<List<Article>>())

        if (network.isConnected()) {
            val response = articleApi.getTopArticles(section, API_KEY)

            if (response.isSuccessful) {
                response.body()!!.articles?.forEach {
                    articleDao.insertOrUpdateArticle(it)

                }
                articleDao.getArticle().collect {
                    emit(NewsResult.Success<List<Article>>(it))
                }
            } else {
                emit(NewsResult.DataError<List<Article>>(223))
            }
        } else {
            articleDao.getArticle().collect {
                emit(NewsResult.Success<List<Article>>(it))
            }

        }
    }

}
