package com.example.artinstitutechicago.data.model

import com.google.gson.annotations.SerializedName

data class ResponseLinkArtworks(
    @SerializedName("data")
    val data: List<Artwork>
)
