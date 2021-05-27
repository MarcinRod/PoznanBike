package com.example.poznanbike

import android.content.Context
import android.os.Build
import com.example.poznanbike.database.BikeStationDB
import com.example.poznanbike.network.BikeStation

object Helpers {
    @Suppress("DEPRECATION")
    fun getColor(context: Context, colorId: Int): Int{
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.getColor(colorId)
            } else {
                context.resources.getColor(colorId)
            }
        }
    fun createBikeStationDB(bikeStation: BikeStation) : BikeStationDB{
        return BikeStationDB(
            bikeStation.properties.bikes.toInt(),
            bikeStation.properties.freeRacks.toInt(),
            bikeStation.properties.label,
            bikeStation.properties.updated,
            bikeStation.geometry.coordinates[0],
            bikeStation.geometry.coordinates[1]
            )
    }
    fun createBikeStation(bikeStationDB: BikeStationDB) : BikeStation{

        val coors = listOf<Double>(bikeStationDB.longitude, bikeStationDB.latitude)
        return BikeStation(
            BikeStation.Geometry(coors,"Point"),
            "id",
            BikeStation.Properties(
                "Unknown",
                bikeStationDB.bikes.toString(),
                bikeStationDB.freeRacks.toString(),
                bikeStationDB.label,
                bikeStationDB.updated),
            "Feature"
        )
    }
    fun createBikeStationList(bikeStationsDB: List<BikeStationDB>) : ArrayList<BikeStation>{
        val arrayList = ArrayList<BikeStation>()
        for(bikeStationDB in bikeStationsDB){
            arrayList.add(createBikeStation(bikeStationDB))
        }
        return arrayList
    }
}
