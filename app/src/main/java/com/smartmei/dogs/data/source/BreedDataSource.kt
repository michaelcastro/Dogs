package com.smartmei.dogs.data.source

import com.smartmei.dogs.data.Breed

interface BreedDataSource {
    fun getAllBreeds(): Breed?
}