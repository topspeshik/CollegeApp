package com.example.eldiploma.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "student_group",
    foreignKeys = [
        ForeignKey(
            entity = StudentDbModel::class,
            parentColumns = ["id"],
            childColumns = ["studentId"]
        ),
//        ForeignKey(
//            entity = GroupDbModel::class,
//            parentColumns = ["id"],
//            childColumns = ["groupId"]
//        )
    ]
    )
data class StudentGroupDbModel(
    @PrimaryKey val id: String,
    val studentId: String,
    val name: String?,
    val groupId: String?,
    val groupName: String?
)
