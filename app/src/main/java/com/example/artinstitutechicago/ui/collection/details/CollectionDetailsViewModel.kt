package com.example.artinstitutechicago.ui.collection.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artinstitutechicago.data.api.ApiHelper
import com.example.artinstitutechicago.data.api.RetrofitBuilder
import com.example.artinstitutechicago.data.model.Artwork
import com.example.artinstitutechicago.data.model.ResponseLinkArtwork
import com.example.artinstitutechicago.data.repository.CollectionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log.v as v1

class CollectionDetailsViewModel : ViewModel() {

    private val apiHelper = ApiHelper(RetrofitBuilder.apiService)
    private val collectionRepository: CollectionRepository = CollectionRepository(apiHelper)
    private val _artwork: MutableLiveData<Artwork> = MutableLiveData()
    val artwork: LiveData<Artwork> = _artwork

    fun loadArtworkInfo(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = collectionRepository.getArtwork(id)
            _artwork.postValue(result.data)
            v1("detailF", " hvhv ${apiHelper.getArtwork(id)}")
        }
    }
}