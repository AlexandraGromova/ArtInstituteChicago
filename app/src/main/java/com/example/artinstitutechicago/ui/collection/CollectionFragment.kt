package com.example.artinstitutechicago.ui.collection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artinstitutechicago.R
import com.example.artinstitutechicago.ui.collection.details.CollectionDetailsFragment

private const val TAG = "frag"

class CollectionFragment : Fragment(R.layout.fragment_collection) {

    private val collectionViewModel: CollectionViewModel by lazy { ViewModelProvider(this).get(CollectionViewModel::class.java) }
    private val adapter: CollectionAdapter by lazy { CollectionAdapter(arrayListOf()) }
    private lateinit var recyclerView: RecyclerView

    private val artworkClickListener by lazy {
        CollectionAdapter.ArtworkClickListener { id ->
            val collectionDetailsFragment = CollectionDetailsFragment()
            val bundle = Bundle()
            Log.v(TAG, "setupObservers ${id}")
            bundle.putString(CollectionDetailsFragment.ARTWORK_ID, id)
            collectionDetailsFragment.arguments = bundle
            findNavController().navigate(R.id.action_navigation_collection_to_navigation_details_collection, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collection, container, false)

        view.apply {
            recyclerView = findViewById(R.id.recyclerView)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
        collectionViewModel.getArtworks()
    }

    private fun setupUI() {
        adapter.artworkClickListener = artworkClickListener
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        collectionViewModel.collection.observe(viewLifecycleOwner) { collection ->
            collection ?: return@observe
            adapter.addCollection(collection)
            Log.v(TAG, "setupObservers $collection")
        }
    }


}

