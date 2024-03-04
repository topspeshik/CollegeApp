package com.example.eldiploma.domain.entity

data class Attendance(
    val id: String?,
    val studentId: String?,
    val isPresent: Boolean?,
    val meetingId: String?,
    val meetingName: String?,
    val contactId: String?,
    val contactName: String?
)
