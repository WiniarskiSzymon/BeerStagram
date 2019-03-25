package com.projects.bigswierku.beerstagram

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class LasLocationProvider @Inject constructor(private val context : Context){
    private lateinit var lastKnownLocation  : Location
    private lateinit var fusedLocationClient : FusedLocationProviderClient

    init{
        registerForLocationChanges()
    }

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation() : Location?{
        return when {
            ::lastKnownLocation.isInitialized -> lastKnownLocation
            else -> null
        }
    }


    @SuppressLint("MissingPermission")
    private fun registerForLocationChanges(){
        if(!::fusedLocationClient.isInitialized){
            getFuseLocationClient()
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if(location!=null){
                    lastKnownLocation = location
                }
                else{
                    context.showMyDialog("Can't obtain location")
                }
            }
    }

    private fun getFuseLocationClient(){

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }
}