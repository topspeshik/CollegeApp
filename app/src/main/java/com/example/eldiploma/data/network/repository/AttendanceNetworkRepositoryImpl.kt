package com.example.eldiploma.data.network.repository

import com.example.eldiploma.data.network.ApiService
import com.example.eldiploma.data.network.mapper.toEntity
import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.entity.Group
import com.example.eldiploma.domain.network.repository.AttendanceNetworkRepository
import com.example.eldiploma.domain.network.repository.GroupNetworkRepository
import com.google.gson.JsonObject
import javax.inject.Inject

class AttendanceNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AttendanceNetworkRepository {
    override suspend fun getAttendance(): List<Attendance> {
        return apiService.getAttendance().list.map { it.toEntity() }
    }

    override suspend fun updateAttendance(id: String, present: JsonObject) {
        apiService.updateAttendance(id,present)
    }
}