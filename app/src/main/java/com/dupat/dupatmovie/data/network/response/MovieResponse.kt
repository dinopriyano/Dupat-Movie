package com.dupat.dupatmovie.data.network.response

import com.dupat.dupatmovie.data.network.model.MovieModel
import com.google.gson.annotations.SerializedName

class MovieResponse (
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: List<MovieModel>? = null
)