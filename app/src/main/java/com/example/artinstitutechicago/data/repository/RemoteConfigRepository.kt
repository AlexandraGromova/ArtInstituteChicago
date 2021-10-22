package com.example.artinstitutechicago.data.repository


import android.util.Log
import android.widget.Toast
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class RemoteConfigRepository(private val remoteConfig : FirebaseRemoteConfig) {
    fun fetchConfig(){
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("k", "Config params updated")
                } else {
                    Log.d("k", "Config params unupdated")
                }
            }
    }
}