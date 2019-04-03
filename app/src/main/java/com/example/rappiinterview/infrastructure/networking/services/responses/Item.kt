package com.example.rappiinterview.infrastructure.networking.services.responses

import io.realm.RealmList
import io.realm.RealmObject

// In order to use Realm I need to make this class and its attributes open.
// Otherwise, I would use a simple data class for this DTO.
open class Item(
    open var adult: Boolean = false,
    open var backdrop_path: String = "",
    open var genre_ids: RealmList<Int>? = null,
    open var id: Int = 0,
    open var media_type: String = "",
    open var original_language: String = "",
    open var original_title: String = "",
    open var overview: String = "",
    open var popularity: Double = 0.0,
    open var poster_path: String = "",
    open var release_date: String = "",
    open var title: String = "",
    open var video: Boolean = false,
    open var vote_average: Double = 0.0,
    open var vote_count: Int = 0,
    open var category: String = "general"
) : RealmObject() {
    companion object {
        const val CATEGORY_FIELD_NAME = "category"
    }
}