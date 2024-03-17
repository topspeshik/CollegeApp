package com.example.eldiploma.data.local.repository

import com.example.eldiploma.data.local.dao.AttendanceDao
import com.example.eldiploma.data.local.dao.GroupDao
import com.example.eldiploma.data.mapper.toDbModel
import com.example.eldiploma.data.mapper.toEntities
import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.local.repository.AttendanceRepository
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(
    private val attendanceDao: AttendanceDao
)  : AttendanceRepository {
    override suspend fun getAttendance(): List<Attendance> {
        return attendanceDao.getAttendance().toEntities()

    }

    override suspend fun addAttendance(attendance: List<Attendance>) {
        attendanceDao.addAttendance(attendance.map{it.toDbModel()})
    }
}