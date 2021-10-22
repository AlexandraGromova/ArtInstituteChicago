package com.example.artinstitutechicago.ui.collection

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artinstitutechicago.R
import com.example.artinstitutechicago.data.model.Artwork


class CollectionAdapter(private val artworks: ArrayList<Artwork>) :
    RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    var artworkClickListener: ArtworkClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(artwork: Artwork) {
            itemView.apply {
                val titleTextView = this.findViewById<TextView>(R.id.title)
                val artistTextView = this.findViewById<TextView>(R.id.artist)
                val imageArtView = this.findViewById<ImageView>(R.id.image_art)
                val container = this.findViewById<ConstraintLayout>(R.id.artworkItem)

                titleTextView.text = artwork.title
                artistTextView.text = artwork.artist_title
                var artworkImageId = artwork.image_id

                Glide
                    .with(this)
                    .load("https://www.artic.edu/iiif/2/${artworkImageId}/full/843,/0/default.jpg")
                    .into(imageArtView)

                container.setOnClickListener {
                    artworkClickListener?.onClick(artwork.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.artwork_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artworks[position])
    }

    override fun getItemCount(): Int = artworks.size

    @SuppressLint("NotifyDataSetChanged")
    fun addCollection(artworks: List<Artwork>) {
        this.artworks.apply {
            clear()
            addAll(artworks)
        }
        notifyDataSetChanged()
    }

    fun interface ArtworkClickListener {
        fun onClick(id: String)
    }
}