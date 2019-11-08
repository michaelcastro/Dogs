package com.smartmei.dogs.data.source.remote

import com.smartmei.dogs.data.Breed
import com.smartmei.dogs.data.BreedImage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsAPI {
    @GET("breeds/list/all")
    fun getAllBreeds() : Call<Breed>

    @GET("breed/{breed_name}/images/random/{page}")
    fun getBreedImages(@Path("breed_name") breedName: String, @Path("page") pagination: Int) : Call<BreedImage?>
}