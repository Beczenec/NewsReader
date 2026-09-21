package com.example.newsreader.ui

import com.example.newsreader.data.model.Article

sealed interface NewsUiState {
    data object Loading : NewsUiState
    data class Success(val articles: List<Article>) : NewsUiState
    data class Error(val message: String) : NewsUiState
}
