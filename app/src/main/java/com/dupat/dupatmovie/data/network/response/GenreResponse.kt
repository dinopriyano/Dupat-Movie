package com.dupat.dupatmovie.data.network.response

import com.dupat.dupatmovie.data.network.model.GenreModel
import com.google.gson.annotations.SerializedName

class GenreResponse (
    @SerializedName("genres") var genres: List<GenreModel>
)