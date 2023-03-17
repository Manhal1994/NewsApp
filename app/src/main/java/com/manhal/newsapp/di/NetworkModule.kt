package com.manhal.newsapp.di

import com.manhal.newsapp.data.NewsRepository
import com.manhal.newsapp.data.NewsRepositoryImpl
import com.manhal.newsapp.data.database.ArticleDao
import com.manhal.newsapp.data.network.ArticleApi
import com.manhal.newsapp.data.network.RemoteData
import com.manhal.newsapp.data.network.RemoteDataImpl
import com.manhal.newsapp.utils.Network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val API_BASE_URL = "https://api.nytimes.com/svc/topstories/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)

            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleApi(retrofit: Retrofit): ArticleApi = retrofit.create(ArticleApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataImpl(articleApi: ArticleApi): RemoteData {
        return RemoteDataImpl(articleApi)
    }

    @Provides
    @Singleton
    fun provideNewsRepositoryImpl(
        articleApi: ArticleApi,
        articleDao: ArticleDao,
        network: Network
    ): NewsRepository {
        return NewsRepositoryImpl(articleApi, articleDao, network)
    }
}