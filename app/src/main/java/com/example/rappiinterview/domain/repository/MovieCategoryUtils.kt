package com.example.rappiinterview.domain.repository

class MovieCategoryUtils {
    fun getCategory(category: String) =
        when (category) {
            "popular" -> MovieCategory.POPULAR
            "top_rated" -> MovieCategory.TOP_RATED
            "upcoming" -> MovieCategory.UPCOMING
            else -> MovieCategory.GENERAL
        }

    fun getCategory(category: MovieCategory) =
        when (category) {
            MovieCategory.POPULAR -> "popular"
            MovieCategory.TOP_RATED -> "top_rated"
            MovieCategory.UPCOMING -> "upcoming"
            else -> "general"
        }
}

enum class MovieCategory {
    POPULAR, TOP_RATED, UPCOMING, GENERAL
}