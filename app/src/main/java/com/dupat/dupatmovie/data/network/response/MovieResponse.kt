package com.dupat.dupatmovie.data.network.response

import com.dupat.dupatmovie.data.network.model.MovieModel
import com.google.gson.annotations.SerializedName

class MovieResponse (
    @SerializedName("page") var page: Int? = null,
    @SerializedName("total_results") var total_results: Int? = null,
    @SerializedName("total_pages") var total_pages: Int? = null,
    @SerializedName("results") var results: List<MovieModel>? = null
)