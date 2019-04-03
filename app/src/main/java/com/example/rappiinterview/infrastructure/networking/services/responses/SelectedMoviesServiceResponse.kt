package com.example.rappiinterview.infrastructure.networking.services.responses

import com.example.rappiinterview.domain.model.Item

data class SelectedMoviesServiceResponse(
    val page: Int,
    val results: List<Item>,
    val total_pages: Int,
    val total_results: Int
)