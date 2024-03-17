package com.example.eldiploma.data.network.dto

import com.google.gson.annotations.SerializedName

data class MeetingDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("dateStart")
    val dateStart: String?,
    @SerializedName("dateEnd")
    val dateEnd: String?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("classroom")
    val classroom: String?,
    @SerializedName("groupId")
    val groupId: String?,
    @SerializedName("groupName")
    val groupName: String?,
)
