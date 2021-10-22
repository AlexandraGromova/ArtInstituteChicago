package com.example.artinstitutechicago.data.model

import com.google.gson.annotations.SerializedName

data class ResponseLinkArtwork(
    @SerializedName("data")
    val data: Artwork
)
