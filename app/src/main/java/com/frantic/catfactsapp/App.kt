package com.frantic.catfactsapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.frantic.catfactsapp.data.db.CatFactsDataBase
import com.frantic.catfactsapp.data.net.retrofit.CatFactsAPI
import com.frantic.catfactsapp.other.navigation.Router
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        var catFactsDataBase: CatFactsDataBase? = null
        var fragmentRouter = Router()
        var currentUserId: Long? = null
        var catFactsAPI: CatFactsAPI = Retrofit
            .Builder()
            .baseUrl("https://cat-fact.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatFactsAPI::class.java)
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        catFactsDataBase = CatFactsDataBase.getInstance(context!!)
    }

}