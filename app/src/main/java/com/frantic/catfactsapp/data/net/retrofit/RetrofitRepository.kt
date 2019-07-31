package com.frantic.catfactsapp.data.net.retrofit

import android.util.Log
import com.frantic.catfactsapp.App

object RetrofitRepository {
    suspend fun getCatFactList(): List<CatFact>?{
        var catFactList: List<CatFact>? = null
        try {
            catFactList = App.catFactsAPI.getRandomCatFactsList().body()
        } catch (e:Exception){
            Log.d("cat_facts_log", "$e")
        }
        return catFactList
    }
}