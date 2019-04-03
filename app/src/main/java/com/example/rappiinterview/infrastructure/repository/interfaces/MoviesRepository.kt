package com.example.rappiinterview.infrastructure.repository.interfaces

import com.example.rappiinterview.infrastructure.util.MovieCategory
import com.example.rappiinterview.domain.model.Item

interface MoviesRepository {
    fun saveMovies(movies: List<Item>?)
    fun saveMoviesWithCategory(movies: List<Item>?, category: String)
    fun getMovies(): List<Item>
    fun getMoviesWithCategory(category: MovieCategory): List<Item>
    fun clearRepository()
    fun close()
}