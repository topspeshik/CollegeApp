package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.local.repository.AttendanceRepository
import javax.inject.Inject

class AddAttendanceUseCase @Inject constructor(
    private val repository: AttendanceRepository
) {
    suspend operator fun invoke(attendance: List<Attendance>) = repository.addAttendance(attendance)
}