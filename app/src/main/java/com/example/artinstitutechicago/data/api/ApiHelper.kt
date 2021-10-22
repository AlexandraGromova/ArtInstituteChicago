package com.example.artinstitutechicago.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getArtworks() = apiService.getArtworks()
    suspend fun getArtwork(id : String) = apiService.getArtwork(id)

}