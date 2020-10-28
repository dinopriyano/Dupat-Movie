package com.dupat.dupatmovie.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dupat.dupatmovie.data.network.model.GenreModel
import com.dupat.dupatmovie.data.network.model.MovieModel
import com.dupat.dupatmovie.data.repositories.BannerRepository
import com.dupat.dupatmovie.data.repositories.GenreRepository
import com.dupat.dupatmovie.data.repositories.LoginRepository
import com.dupat.dupatmovie.data.repositories.MovieRepository
import com.dupat.dupatmovie.ui.utils.APIExceptions
import com.dupat.dupatmovie.ui.utils.Corountines
import com.dupat.dupatmovie.ui.utils.SingleLiveEvent

class MovieViewModel : ViewModel(){
    private var movies = MutableLiveData<List<MovieModel>>()
    private var banner = MutableLiveData<List<MovieModel>>()
    private var movie = MutableLiveData<MovieModel>()
    private var genres = MutableLiveData<List<GenreModel>>()
    private var state : SingleLiveEvent<MovieState> = SingleLiveEvent()

    fun fetchAllMovie(page: Int){
        Corountines.main {
            try {
                val response = MovieRepository().fetchAllMovie(page)
                response.let {
                    movies.postValue(it.results)

                    return@main
                }

            }
            catch (e: APIExceptions)
            {
                state.value = MovieState.Error(e.message)
            }
        }
    }

    fun fetchBanner(page: Int){
        Corountines.main {
            try {
                val response = BannerRepository().movieBanner(page)
                response.let {
                    banner.postValue(it.results)

                    return@main
                }

            }
            catch (e: APIExceptions)
            {
                state.value = MovieState.Error(e.message)
            }
        }
    }

    fun fetchGenres(){
        Corountines.main {
            try {
                val response = GenreRepository().movieGenre()
                response.let {
                    genres.postValue(it.genres)
                    return@main
                }

            }
            catch (e: APIExceptions)
            {
                state.value = MovieState.Error(e.message)
            }
        }
    }

    fun getMovies() = movies
    fun getBanner() = banner
    fun getMovie() = movie
    fun getState() = state
    fun getGenres() = genres
}

sealed class MovieState {
    data class ShowToast(var message : String) : MovieState()
    data class IsLoading(var state : Boolean = false) : MovieState()
    data class MovieValidation(var title : String? = null, var content : String? = null) : MovieState()
    data class Error(var err : String?) : MovieState()
    data class IsSuccess(var what : Int? = null) : MovieState()
    object Reset : MovieState()
}