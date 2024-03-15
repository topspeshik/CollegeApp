package com.example.eldiploma.data.network.dto

import com.google.gson.annotations.SerializedName

data class StudentGroupDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("contactId")
    val studentId: String,

    @SerializedName("contactName")
    val name: String?,

    @SerializedName("groupId")
    val groupId: String,

    @SerializedName("groupName")
    val groupName: String?
)
