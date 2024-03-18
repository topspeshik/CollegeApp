package com.example.eldiploma.domain.network.usecase

import com.example.eldiploma.domain.network.repository.AttendanceNetworkRepository
import com.google.gson.JsonObject
import javax.inject.Inject

class UpdateAttendanceUseCase @Inject constructor(
    private val repository: AttendanceNetworkRepository
) {
    suspend operator fun invoke(id: String, present: JsonObject) = repository.updateAttendance(id,present)
}