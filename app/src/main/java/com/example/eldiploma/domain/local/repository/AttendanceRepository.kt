package com.example.eldiploma.domain.local.repository

import com.example.eldiploma.domain.entity.Attendance
import kotlinx.coroutines.flow.Flow

interface AttendanceRepository {

    suspend fun getAttendance(): Flow<List<Attendance>>

    suspend fun addAttendanceList(attendance: List<Attendance>)

    suspend fun addAttendance(attendance: Attendance)

     fun getAttendanceWithMeeting(groupId: String,date: String) : Flow<List<Attendance>>

     fun getAttendanceMeeting(groupId: String) : Flow<List<Attendance>>

}