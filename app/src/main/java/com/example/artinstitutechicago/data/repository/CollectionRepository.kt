package com.example.artinstitutechicago.data.repository

import com.example.artinstitutechicago.data.api.ApiHelper
import com.example.artinstitutechicago.data.model.Artwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CollectionRepository(
    private val apiHelper: ApiHelper) {

    suspend fun getArtworks() = apiHelper.getArtworks()

    suspend fun getArtwork(id: String) = apiHelper.getArtwork(id)
}