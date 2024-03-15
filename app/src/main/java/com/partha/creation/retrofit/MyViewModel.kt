package com.partha.creation.retrofit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.partha.creation.pojo.MovieResponse
import com.partha.creation.retrofit.RetrofitRepository
import com.partha.creation.room.AppDatabase
import com.partha.creation.room.RoomRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MyViewModel : ViewModel() {

    private val repository = RetrofitRepository()
    private val _movieResponse = MutableLiveData<MovieResponse>()
    val movieResponse: LiveData<MovieResponse> = _movieResponse

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchMovies(page: String) {
        viewModelScope.launch {
            try {
                val response = repository.fetchManga(page)
                if (response.isSuccessful) {
                    _movieResponse.value = response.body()
                } else {
                    _errorMessage.value = "Error: ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}
