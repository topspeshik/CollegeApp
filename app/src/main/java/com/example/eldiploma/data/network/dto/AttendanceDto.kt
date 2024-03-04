package com.example.eldiploma.data.network.dto

import com.google.gson.annotations.SerializedName

data class AttendanceDto(
    @SerializedName("id")
    val id: String?,

    @SerializedName("contactId")
    val studentId: String?,

    @SerializedName("isPresent")
    val isPresent: Boolean?,

    @SerializedName("meetingId")
    val meetingId: String?,

    @SerializedName("meetingName")
    val meetingName: String?,

    @SerializedName("contactId")
    val contactId: String?,

    @SerializedName("contactName")
    val contactName: String?
)
