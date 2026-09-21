package com.example.newsreader.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsreader.data.model.Article
import com.example.newsreader.data.remote.NewsApiService
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val api: NewsApiService) {
    fun getNewsStream(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(api) }
        ).flow
    }

    suspend fun getTopHeadlines(): List<Article> {
        val response = api.getTopHeadlines()
        if (response.status != "ok") {
            throw IllegalStateException("NewsAPI returned an unsuccessful response.")
        }
        return response.articles
    }
}
