package com.dupat.dupatmovie.data.network.model

import com.google.gson.annotations.SerializedName

class MovieModel(
    @SerializedName("poster_path") var poster_path: String? = null,
    @SerializedName("popularity") var popularity: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("original_title") var original_title: String? = null,
    @SerializedName("vote_average") var vote_average: String? = null
)