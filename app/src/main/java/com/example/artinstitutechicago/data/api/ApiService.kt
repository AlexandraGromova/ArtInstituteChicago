package com.example.artinstitutechicago.data.api

import com.example.artinstitutechicago.data.model.Artwork
import com.example.artinstitutechicago.data.model.ResponseLinkArtwork
import com.example.artinstitutechicago.data.model.ResponseLinkArtworks
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("artworks")
    suspend fun getArtworks(): ResponseLinkArtworks

    @GET("artworks/{id}")
    suspend fun getArtwork(@Path("id")id: String): ResponseLinkArtwork

}