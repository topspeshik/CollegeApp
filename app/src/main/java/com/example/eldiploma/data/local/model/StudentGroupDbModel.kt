package com.example.eldiploma.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentGroup")
data class StudentGroupDbModel(
    @PrimaryKey val id: String,
    val studentId: String?,
    val name: String?,
    val groupId: String?,
    val groupName: String?
)
