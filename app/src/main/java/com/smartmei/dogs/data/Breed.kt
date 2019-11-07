package com.smartmei.dogs.data

import com.google.gson.annotations.SerializedName

class Breed {
    @SerializedName("status")
    val status: String? = null
    @SerializedName("message")
    val message: Map<String, List<String>>? = null

}