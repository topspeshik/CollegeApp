package com.example.eldiploma.domain.entity

data class Attendance(
    val id: String,
    val studentId: String?,
    var isPresent: Boolean?,
    val meetingId: String?,
    val meetingName: String?,
    val studentName: String?
)
