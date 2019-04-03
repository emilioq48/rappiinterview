package com.example.rappiinterview.infrastructure.networking.services.responses

data class TopRatedMoviesServiceResponse(
    val page: Int,
    val results: List<Item>,
    val total_pages: Int,
    val total_results: Int
)