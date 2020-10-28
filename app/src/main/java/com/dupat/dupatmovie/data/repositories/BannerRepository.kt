package com.dupat.dupatmovie.data.repositories

import com.dupat.dupatmovie.data.network.APIInterface
import com.dupat.dupatmovie.data.network.SafeAPIRequest
import com.dupat.dupatmovie.data.network.response.LoginResponse
import com.dupat.dupatmovie.data.network.response.MovieResponse
import com.dupat.dupatmovie.ui.utils.Constant

class BannerRepository : SafeAPIRequest() {
    suspend fun movieBanner(page: Int) : MovieResponse {
        return apiRequest { APIInterface(Constant.baseMovie).getBanner(Constant.api_key,page) }
    }
}