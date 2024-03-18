package com.example.eldiploma.domain.local.repository

import com.example.eldiploma.domain.entity.Attendance
import kotlinx.coroutines.flow.Flow

interface AttendanceRepository {

    suspend fun getAttendance(): List<Attendance>

    suspend fun addAttendanceList(attendance: List<Attendance>)

    suspend fun addAttendance(attendance: Attendance)

     fun getAttendanceWithMeeting(groupId: String) : Flow<List<Attendance>>

}