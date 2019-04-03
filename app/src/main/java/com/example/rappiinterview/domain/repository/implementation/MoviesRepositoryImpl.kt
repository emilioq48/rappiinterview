package com.example.rappiinterview.domain.repository.implementation

import android.util.Log
import com.example.rappiinterview.domain.repository.MovieCategory
import com.example.rappiinterview.domain.repository.MovieCategoryUtils
import com.example.rappiinterview.domain.repository.interfaces.MoviesRepository
import com.example.rappiinterview.infrastructure.networking.services.responses.Item
import com.example.rappiinterview.infrastructure.networking.services.responses.Item.Companion.CATEGORY_FIELD_NAME
import io.realm.Realm

class MoviesRepositoryImpl(private val moviesUtils: MovieCategoryUtils) : MoviesRepository {
    companion object {
        private const val TAG = "MoviesRepositoryImpl"
    }

    private var realm = Realm.getDefaultInstance()

    override fun saveMovies(movies: List<Item>?) {
        movies?.forEach {
            try {
                it.category = moviesUtils.getCategory(MovieCategory.GENERAL)
                realm.beginTransaction()
                realm.copyToRealm(it)
                realm.commitTransaction()
            } catch (exception: Throwable) {
                Log.d(TAG, exception.message)
            }
        }
    }

    override fun saveMoviesWithCategory(movies: List<Item>?, category: String) {
        movies?.forEach {
            try {
                it.category = category
                realm.beginTransaction()
                realm.copyToRealm(it)
                realm.commitTransaction()
            } catch (exception: Throwable) {
                Log.d(TAG, exception.message)
            }
        }
    }

    override fun close() {
        realm.close()
    }

    override fun getMovies(): List<Item> = realm.where(Item::class.java).findAll()

    override fun getMoviesWithCategory(category: MovieCategory): List<Item> =
        realm.where(Item::class.java).contains(CATEGORY_FIELD_NAME, moviesUtils.getCategory(category)).findAll()

    override fun clearRepository() {
//        realm.beginTransaction()
//        realm.delete(Item::class.java)
//        realm.commitTransaction()
    }
}