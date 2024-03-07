package com.example.eldiploma.data.network.dto

import com.google.gson.annotations.SerializedName

data class AttendanceResponseDto (
    @SerializedName("total")
    val total: Int,

    @SerializedName("list")
    val list: List<AttendanceDto>
)
