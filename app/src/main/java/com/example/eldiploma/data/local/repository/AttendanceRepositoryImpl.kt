package com.example.eldiploma.data.local.repository

import com.example.eldiploma.data.local.dao.AttendanceDao
import com.example.eldiploma.data.mapper.toDbModel
import com.example.eldiploma.data.mapper.toEntities
import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.local.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(
    private val attendanceDao: AttendanceDao
)  : AttendanceRepository {
    override suspend fun getAttendance(): List<Attendance> {
        return attendanceDao.getAttendance().toEntities()

    }

    override suspend fun addAttendanceList(attendance: List<Attendance>) {
        attendanceDao.addAttendanceList(attendance.map{it.toDbModel()})
    }

    override suspend fun addAttendance(attendance: Attendance) {
        attendanceDao.addAttendance(attendance.toDbModel())

    }

    override fun getAttendanceWithMeeting(groupId: String): Flow<List<Attendance>> {
        return attendanceDao.getAttendanceWithMeeting(groupId).map { it.toEntities() }

    }


}