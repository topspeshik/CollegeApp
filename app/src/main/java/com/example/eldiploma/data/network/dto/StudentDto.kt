package com.example.eldiploma.data.network.dto

import com.google.gson.annotations.SerializedName

data class StudentDto (
    @SerializedName("id")
    val id: String,
    @SerializedName("firstName")
    val name: String?,
    @SerializedName("lastName")
    val lastname: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?
)