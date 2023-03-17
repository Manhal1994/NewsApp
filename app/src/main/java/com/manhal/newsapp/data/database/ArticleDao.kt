package com.manhal.newsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manhal.newsapp.data.database.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateArticle(article: Article)

    @Query("SELECT * from article")
    fun getArticle(): Flow<List<Article>>
}