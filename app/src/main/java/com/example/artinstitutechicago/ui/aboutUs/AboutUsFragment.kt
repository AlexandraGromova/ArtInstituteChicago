package com.example.artinstitutechicago.ui.aboutUs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.artinstitutechicago.FirebaseConfig
import com.example.artinstitutechicago.R
import com.example.artinstitutechicago.databinding.FragmentAboutUsBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class AboutUsFragment : Fragment(R.layout.fragment_about_us) {

    private lateinit var aboutUsViewModel: AboutUsViewModel
    private var _binding: FragmentAboutUsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutUsViewModel =
            ViewModelProvider(this).get(AboutUsViewModel::class.java)

        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firebaseConfig = FirebaseConfig()
        firebaseConfig.provideConfig()
        Firebase.remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}