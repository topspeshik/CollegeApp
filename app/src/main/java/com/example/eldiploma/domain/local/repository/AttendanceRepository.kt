package com.example.eldiploma.domain.local.repository

import com.example.eldiploma.domain.entity.Attendance

interface AttendanceRepository {

    suspend fun getAttendance(): List<Attendance>

    suspend fun addAttendance(attendance: List<Attendance>)

}