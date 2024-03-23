package com.example.eldiploma.data.network.dto

import com.google.gson.annotations.SerializedName

data class TeacherResponseDto (
    @SerializedName("total")
    val total: Int,

    @SerializedName("list")
    val list: List<TeacherDto>
)
