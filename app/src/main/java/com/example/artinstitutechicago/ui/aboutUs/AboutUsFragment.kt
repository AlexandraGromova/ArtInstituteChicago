package com.example.artinstitutechicago.ui.aboutUs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.artinstitutechicago.FirebaseConfig
import com.example.artinstitutechicago.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class AboutUsFragment : Fragment(R.layout.fragment_about_us) {

    private lateinit var aboutUsViewModel: AboutUsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firebaseConfig = FirebaseConfig()
        firebaseConfig.provideConfig()
        Firebase.remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        firebaseConfig.activateConfig()
        val str = Firebase.remoteConfig.getString("text_about_institute")
        val textView = view.findViewById<TextView>(R.id.text_about_institute)
        val imageView = view.findViewById<ImageView>(R.id.image_institute)
        textView.text = str
        Glide
            .with(this)
            .load("https://www.artic.edu/iiif/2/974cfe72-6d5f-3e47-4958-a2c64afa3224/full/843,/0/default.jpg")
            .into(imageView)

    }

}