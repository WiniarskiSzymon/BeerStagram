package com.projects.bigswierku.beerstagram

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class LasLocationProvider @Inject constructor(private val context : Context){
    private lateinit var fusedLocationClient : FusedLocationProviderClient



    @SuppressLint("MissingPermission")
    fun getLastKnownLocation() : Task<Location> {
        if(!::fusedLocationClient.isInitialized){
            getFuseLocationClient()
        }
        return fusedLocationClient.lastLocation

    }




    private fun getFuseLocationClient(){

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }
}