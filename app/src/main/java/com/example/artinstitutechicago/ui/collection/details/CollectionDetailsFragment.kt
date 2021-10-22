package com.example.artinstitutechicago.ui.collection.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.artinstitutechicago.R
import com.example.artinstitutechicago.data.model.Artwork


 private const val TAG = "detailF"
class CollectionDetailsFragment : Fragment(R.layout.fragment_details_collection) {

    private val id by lazy { arguments?.getString(ARTWORK_ID) }
    private val viewModel: CollectionDetailsViewModel by lazy { ViewModelProvider(this).get(CollectionDetailsViewModel::class.java) }

    private val artworkObserver = Observer<Artwork?> {
        it ?: return@Observer
        Log.v(TAG, "setupObservers2 ${it}")

        val imageArtView =  requireView().findViewById<ImageView>(R.id.imageView2)
        val titleTextView = requireView().findViewById<TextView>(R.id.titleTextView)
        val historyTextView = requireView().findViewById<TextView>(R.id.historyArtworkTextView)
        var artworkImageId  = it.image_id
        titleTextView.text = it.title
        historyTextView.text = it.artist_title

        Glide
            .with(this)
            .load("https://www.artic.edu/iiif/2/${artworkImageId}/full/843,/0/default.jpg")
            .into(imageArtView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v(TAG, "setupObservers oooo2")
        id?.let { viewModel.loadArtworkInfo(it) }
        Log.v(TAG, "setupObservers oooo $id")
        viewModel.artwork.observe(viewLifecycleOwner, artworkObserver)
        Log.v(TAG, "setupObservers fff ${viewModel.artwork.value}")

    }

    companion object {
        const val ARTWORK_ID = "id"
    }
}