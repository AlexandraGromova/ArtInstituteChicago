package com.example.artinstitutechicago.ui.collection.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.artinstitutechicago.R
import com.example.artinstitutechicago.data.model.Artwork


class CollectionDetailsFragment : Fragment(R.layout.fragment_details_collection) {

    private val id by lazy { arguments?.getString(ARTWORK_ID) }
    private val viewModel: CollectionDetailsViewModel by lazy {
        ViewModelProvider(this).get(
            CollectionDetailsViewModel::class.java
        )
    }

    @SuppressLint("SetTextI18n")
    private val artworkObserver = Observer<Artwork?> {
        it ?: return@Observer

        val artworkImageId = it.image_id
        val imageArtView = requireView().findViewById<ImageView>(R.id.imageView2)
        val titleTextView = requireView().findViewById<TextView>(R.id.titleTextView)
        val artistTextView = requireView().findViewById<TextView>(R.id.artist)
        val titleArtDate = requireView().findViewById<TextView>(R.id.date)
        val departmentTextView = requireView().findViewById<TextView>(R.id.department)
        val dimensionTextView = requireView().findViewById<TextView>(R.id.dimensions)
        titleArtDate.text = it.date_display
        titleTextView.text = it.title
        artistTextView.text = it.artist_title
        departmentTextView.text = it.department_title
        dimensionTextView.text = it.dimensions

        Glide
            .with(this)
            .load("https://www.artic.edu/iiif/2/${artworkImageId}/full/843,/0/default.jpg")
            .into(imageArtView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_Bar)
        viewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            if (isLoading == true) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        }

        id?.let { viewModel.loadArtworkInfo(it) }
        viewModel.artwork.observe(viewLifecycleOwner, artworkObserver)
    }

    companion object {
        const val ARTWORK_ID = "id"
    }
}