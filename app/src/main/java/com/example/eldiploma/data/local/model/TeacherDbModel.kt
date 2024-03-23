package com.example.eldiploma.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "teachers")
data class TeacherDbModel (
    @PrimaryKey val id: String,

    val firstName: String?,

    val lastName: String?,

    val phoneNumber: String?,
)