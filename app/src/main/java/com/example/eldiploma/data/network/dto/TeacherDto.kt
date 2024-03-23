package com.example.eldiploma.data.network.dto

import com.google.gson.annotations.SerializedName

data class TeacherDto (
    @SerializedName("id")
    val id: String,

    @SerializedName("firstName")
    val firstName: String?,

    @SerializedName("lastName")
    val lastName: String?,

    @SerializedName("phoneNumber")
    val phoneNumber: String?,

    @SerializedName("emailAddress")
    val emailAddress: String?,
)