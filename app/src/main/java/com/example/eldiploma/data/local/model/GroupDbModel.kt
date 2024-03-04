package com.example.eldiploma.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class GroupDbModel (
    @PrimaryKey val id: String,
    val name: String?,
    val leadId: String?,
    val leadName: String?
)