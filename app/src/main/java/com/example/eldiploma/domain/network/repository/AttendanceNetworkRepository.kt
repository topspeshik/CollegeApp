package com.example.eldiploma.domain.network.repository

import com.example.eldiploma.domain.entity.Attendance

interface AttendanceNetworkRepository {
    suspend fun getAttendance(): List<Attendance>
}