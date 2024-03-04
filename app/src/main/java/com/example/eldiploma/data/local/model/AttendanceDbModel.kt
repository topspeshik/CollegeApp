package com.example.eldiploma.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance")
data class AttendanceDbModel(
    @PrimaryKey val id: String,
    val studentId: String?,
    val isPresent: Boolean?,
    val meetingId: String?,
    val meetingName: String?,
    val contactId: String?,
    val contactName: String?
)
