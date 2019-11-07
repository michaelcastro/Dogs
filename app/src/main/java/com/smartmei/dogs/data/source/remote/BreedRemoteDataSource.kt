package com.smartmei.dogs.data.source.remote

import com.smartmei.dogs.data.Breed
import com.smartmei.dogs.data.source.BreedDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BreedRemoteDataSource: BreedDataSource {

    private val BASE_URL = "https://dog.ceo/api/breeds/"
    private var breedsAPI: BreedsAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        breedsAPI = retrofit.create(BreedsAPI::class.java)
    }

    override fun getAllBreeds(): Breed? {
        val call = breedsAPI.getAllIsses()
        return call.execute().body()
    }
}