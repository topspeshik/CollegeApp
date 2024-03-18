package com.example.eldiploma.domain.network.repository

import com.example.eldiploma.domain.entity.Attendance
import com.google.gson.JsonObject

interface AttendanceNetworkRepository {
    suspend fun getAttendance(): List<Attendance>

    suspend fun updateAttendance(id: String, present: JsonObject)
}