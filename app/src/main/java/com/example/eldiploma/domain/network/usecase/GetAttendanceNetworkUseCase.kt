package com.example.eldiploma.domain.network.usecase

import com.example.eldiploma.domain.network.repository.AttendanceNetworkRepository
import javax.inject.Inject

class GetAttendanceNetworkUseCase @Inject constructor(
    private val repository: AttendanceNetworkRepository
) {
    suspend operator fun invoke() = repository.getAttendance()
}