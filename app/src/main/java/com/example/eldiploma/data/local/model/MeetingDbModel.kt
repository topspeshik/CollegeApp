package com.example.eldiploma.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meeting")
data class MeetingDbModel(
    @PrimaryKey val id: String,
    val name: String?,
    val dateStart: String?,
    val dateEnd: String?,
    val duration: String?,
    val classroom: String?,
    val groupId: String?,
    val groupName: String?,
)
