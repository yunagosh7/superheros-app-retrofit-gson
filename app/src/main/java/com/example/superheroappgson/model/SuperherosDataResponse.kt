package com.example.superheroappgson.model

import com.google.gson.annotations.SerializedName

data class SuperherosDataResponse (
    @SerializedName("response") val status: String,
    @SerializedName("results") val superherosList: List<SuperherosItemResponse>
        )

data class SuperherosItemResponse (
    @SerializedName("name") val name: String,
    @SerializedName("id")  val id: String
)