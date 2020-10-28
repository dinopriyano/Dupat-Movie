package com.dupat.dupatmovie.data.repositories

import com.dupat.dupatmovie.data.network.APIInterface
import com.dupat.dupatmovie.data.network.SafeAPIRequest
import com.dupat.dupatmovie.data.network.response.GenreResponse
import com.dupat.dupatmovie.data.network.response.MovieResponse
import com.dupat.dupatmovie.ui.utils.Constant

class GenreRepository : SafeAPIRequest() {
    suspend fun movieGenre() : GenreResponse {
        return apiRequest { APIInterface(Constant.baseMovie).getGenre(Constant.api_key) }
    }
}