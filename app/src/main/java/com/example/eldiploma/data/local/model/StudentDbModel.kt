package com.example.eldiploma.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentDbModel (
    @PrimaryKey val id: String,
    val name: String?,
    val lastname: String?,
    val phoneNumber: String?
)