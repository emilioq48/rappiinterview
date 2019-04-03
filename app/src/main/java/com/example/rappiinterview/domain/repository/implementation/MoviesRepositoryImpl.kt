package com.example.rappiinterview.domain.repository.implementation

import android.util.Log
import com.example.rappiinterview.domain.repository.interfaces.MoviesRepository
import com.example.rappiinterview.infrastructure.networking.services.responses.Item
import io.realm.Realm

class MoviesRepositoryImpl : MoviesRepository {
    companion object {
        private const val TAG = "MoviesRepositoryImpl"
    }

    private var realm = Realm.getDefaultInstance()

    override fun saveMovies(movies: List<Item>?) {
        movies?.forEach {
            try {
                realm.beginTransaction()
                realm.copyToRealm(it)
                realm.commitTransaction()
            } catch (exception: Throwable) {
                Log.d(TAG, exception.message)
            }
        }
    }

    override fun getMovies(): List<Item> {
        return realm.where(Item::class.java).findAll()
//        realm.use { realm ->
//            return realm.where(Item::class.java).findAll()
//        }
    }

    override fun clearRepository() {
        realm.beginTransaction()
        realm.delete(Item::class.java)
        realm.commitTransaction()
    }

    override fun getRatedMovies(startRange: Double, endRange: Double): List<Item> {
        return realm.where(Item::class.java).findAll().filter { item ->
            item.vote_average > startRange && item.vote_average < endRange
        }
    }
}