package com.example.poznanbike

import android.content.Context
import android.os.Build
import com.example.poznanbike.database.BikeStationDB
import com.example.poznanbike.network.BikeStation

// Set of helper functions that are common for entire project. A singleton pattern is used here,
// so we don't need to create an instance of a Helper class
object Helpers {
    @Suppress("DEPRECATION")
    fun getColor(context: Context, colorId: Int): Int {
        // Custom implementation of the getColor method that disables the deprecation warning
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getColor(colorId)
        } else {
            context.resources.getColor(colorId) // for older devices (older than API 23) to get the
                                                // color we first had to access resources
        }
    }

    // Function that converts the BikeStation (Retrofit version) to BikeStationDB (database version)
    fun createBikeStationDB(bikeStation: BikeStation): BikeStationDB {
        return BikeStationDB(
            bikeStation.properties.bikes.toInt(),
            bikeStation.properties.freeRacks.toInt(),
            bikeStation.properties.label,
            bikeStation.properties.updated,
            bikeStation.geometry.coordinates[0],
            bikeStation.geometry.coordinates[1]
        )
    }
    // Function that converts the BikeStationSB  (database version)  to BikeStation (Retrofit version)
    fun createBikeStation(bikeStationDB: BikeStationDB): BikeStation {

        val coors = listOf<Double>(bikeStationDB.longitude, bikeStationDB.latitude)
        return BikeStation(
            BikeStation.Geometry(coors, "Point"),
            "id",
            BikeStation.Properties(
                "Unknown",
                bikeStationDB.bikes.toString(),
                bikeStationDB.freeRacks.toString(),
                bikeStationDB.label,
                bikeStationDB.updated
            ),
            "Feature"
        )
    }

    fun createBikeStationList(bikeStationsDB: List<BikeStationDB>): ArrayList<BikeStation> {
        val arrayList = ArrayList<BikeStation>()
        for (bikeStationDB in bikeStationsDB) {
            arrayList.add(createBikeStation(bikeStationDB))
        }
        return arrayList
    }

    fun test() {
        // OUTPUT: => I have 3 dogs and 2 cats
        val numberOfDogs = 3
        val numberOfCats = 2
        val str = "I have $numberOfDogs" + " and $numberOfCats"

        // Jaki będzie wynik wykonania tego kodu
        val results = 21
        when (results) {
            0 -> println("No results")
            in 1..39 -> println("Got results!")
            else -> println("That's a lot of results")
        }

        // Jak zrozumieć poniższy kod
        val adapter = binding.bikeStationsList.adapter ?: return


    }

    // Wskaż argumenty wymagane i domyślne funkcji.
    fun myFunction(str: String, b: Boolean, c: Int = 9, d: Boolean = true): Int {
        return 0
    }

    // Która z funkcji jest funkcją lambda
    fun double(x: Int): Int = x * 2

    fun double2(x: Int): Int {
        return x * 2
    }

    val d = { x: Int -> x * 2 }




}



