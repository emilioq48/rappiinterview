package com.example.rappiinterview.infrastructure.networking.services.responses

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

// In order to use Realm I need to add all this redundant code otherwise I would use a simple data class for this DTO.
public open class Item(
    public open var adult: Boolean = false,
    public open var backdrop_path: String = "",
    public open var genre_ids: RealmList<Int>? = null,
    @PrimaryKey public open var id: Int = 0,
    public open var media_type: String = "",
    public open var original_language: String = "",
    public open var original_title: String = "",
    public open var overview: String = "",
    public open var popularity: Double = 0.0,
    public open var poster_path: String = "",
    public open var release_date: String = "",
    public open var title: String = "",
    public open var video: Boolean = false,
    public open var vote_average: Double = 0.0,
    public open var vote_count: Int = 0
) : RealmObject()