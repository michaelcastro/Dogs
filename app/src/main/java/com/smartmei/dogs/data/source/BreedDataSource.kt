package com.smartmei.dogs.data.source

import com.smartmei.dogs.data.Breed
import com.smartmei.dogs.data.BreedImage

interface BreedDataSource {
    fun getAllBreeds(): Breed?
    fun getBreedImages(breedName: String, pagination: Int): BreedImage?
}