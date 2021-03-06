package com.example.rappiinterview.infrastructure.networking.services.responses

import com.example.rappiinterview.domain.model.Item

data class MoviesServiceResponse(
    val created_by: String,
    val description: String,
    val favorite_count: Int,
    val id: String,
    val iso_639_1: String,
    val item_count: Int,
    val items: List<Item>,
    val name: String,
    val poster_path: String
)