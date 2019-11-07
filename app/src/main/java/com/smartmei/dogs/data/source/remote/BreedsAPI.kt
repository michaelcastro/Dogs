package com.smartmei.dogs.data.source.remote

import com.smartmei.dogs.data.Breed
import retrofit2.Call
import retrofit2.http.GET

interface BreedsAPI {
    @GET("list/all")
    fun getAllIsses() : Call<Breed>
}