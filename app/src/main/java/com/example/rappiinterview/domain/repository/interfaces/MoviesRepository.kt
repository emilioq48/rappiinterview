package com.example.rappiinterview.domain.repository.interfaces

import com.example.rappiinterview.infrastructure.networking.services.responses.Item

interface MoviesRepository {
    fun saveMovies(movies: List<Item>?)
    fun getMovies(): List<Item>
    fun getRatedMovies(startRange: Double, endRange: Double): List<Item>
    fun clearRepository()
}