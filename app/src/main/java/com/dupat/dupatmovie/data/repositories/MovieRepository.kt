package com.dupat.dupatmovie.data.repositories

import com.dupat.dupatmovie.data.network.APIInterface
import com.dupat.dupatmovie.data.network.SafeAPIRequest
import com.dupat.dupatmovie.data.network.response.MovieResponse
import com.dupat.dupatmovie.ui.utils.Constant

class MovieRepository: SafeAPIRequest() {
    suspend fun fetchAllMovie(page: Int) : MovieResponse{
        return apiRequest { APIInterface(Constant.baseMovie).getMovieList(Constant.api_key,page) }
    }

    suspend fun fetchAllMovieByGenre(page: Int, genre: Int) : MovieResponse{
        return apiRequest { APIInterface(Constant.baseMovie).getMovieListByGenre(Constant.api_key,genre,page) }
    }
}