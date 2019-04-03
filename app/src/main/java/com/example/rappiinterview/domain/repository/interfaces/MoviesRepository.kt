package com.example.rappiinterview.domain.repository.interfaces

import com.example.rappiinterview.domain.repository.MovieCategory
import com.example.rappiinterview.infrastructure.networking.services.responses.Item

interface MoviesRepository {
    fun saveMovies(movies: List<Item>?)
    fun saveMoviesWithCategory(movies: List<Item>?, category: String)
    fun getMovies(): List<Item>
    fun getMoviesWithCategory(category: MovieCategory): List<Item>
    fun clearRepository()
    fun close()
}