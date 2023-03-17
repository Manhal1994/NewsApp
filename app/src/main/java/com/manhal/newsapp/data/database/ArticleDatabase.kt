package com.manhal.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manhal.newsapp.data.database.entity.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = true
)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDao: ArticleDao

}