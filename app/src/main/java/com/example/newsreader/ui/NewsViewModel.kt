package com.example.newsreader.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsreader.data.model.Article
import com.example.newsreader.data.remote.RetrofitInstance
import com.example.newsreader.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    val pagingDataFlow: Flow<PagingData<Article>> = repository.getNewsStream()
        .cachedIn(viewModelScope)
}

class NewsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(NewsRepository(RetrofitInstance.api)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
