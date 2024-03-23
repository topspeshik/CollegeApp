package com.example.eldiploma.data.network.dto

import com.google.gson.annotations.SerializedName

data class GroupDto (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("leadId")
    val leadId: String?,
    @SerializedName("leadName")
    val leadName: String?
)