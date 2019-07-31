package com.frantic.catfactsapp.data.net.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface CatFactsAPI {

    @GET("facts/random?animal_type=cat&amount=100")
    suspend fun getRandomCatFactsList(): Response<List<CatFact>>

}