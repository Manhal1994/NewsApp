package com.manhal.newsapp.di

import android.content.Context
import androidx.room.Room
import com.manhal.newsapp.data.database.ArticleDao
import com.manhal.newsapp.data.database.ArticleDatabase
import com.manhal.newsapp.utils.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "articles.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(db: ArticleDatabase): ArticleDao = db.articleDao

    @Provides
    @Singleton
    fun provideNetwork(@ApplicationContext context: Context): Network = Network(context)
}