package com.partha.creation.room

import com.partha.creation.room.entities.Movie
import kotlinx.coroutines.runBlocking

class RoomRepository(private val database: AppDatabase) {
    fun getAllMovies(): List<Movie> {
        return runBlocking { (database.movieDao().getAllMovies()?: ArrayList()) as List<Movie> }
    }
    fun insertMovie(movie: Movie) {
        runBlocking { database.movieDao().insertMovie(movie) }
    }

    fun insertAllMovies(movies: List<Movie>?) {
        runBlocking { database.movieDao().insertAllMovies(movies?:ArrayList()) }
    }

    fun updateMovieFavoriteStatus(movieId: String, isFavorite: Boolean) {
        runBlocking { database.movieDao().updateIsFavorite(movieId, isFavorite) }
    }
}