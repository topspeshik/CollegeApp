package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.local.repository.AttendanceRepository
import javax.inject.Inject

class GetAttendanceUseCase @Inject constructor(
    private val repository: AttendanceRepository
) {
    suspend operator fun invoke() = repository.getAttendance()
}