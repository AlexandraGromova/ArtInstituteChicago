package com.example.artinstitutechicago.ui.collection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artinstitutechicago.data.api.ApiHelper
import com.example.artinstitutechicago.data.api.RetrofitBuilder
import com.example.artinstitutechicago.data.model.Artwork
import com.example.artinstitutechicago.data.repository.CollectionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "ViewModel"

class CollectionViewModel : ViewModel() {

    private var apiHelper = ApiHelper(RetrofitBuilder.apiService)
    private val collectionRepository: CollectionRepository = CollectionRepository(apiHelper)
    private val _collection: MutableLiveData<List<Artwork>> = MutableLiveData()
    val collection: LiveData<List<Artwork>> = _collection

    fun getArtworks() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = collectionRepository.getArtworks()
            _collection.postValue(response.data)
            Log.v(TAG, "w ${response.data}")
        }
        Log.v(TAG, "OOOOOOO ")
    }
}