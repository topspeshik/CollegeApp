package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.local.repository.AttendanceRepository
import javax.inject.Inject

class GetAttendanceMeetingUseCase @Inject constructor(
    private val repository: AttendanceRepository
) {
    operator fun invoke(groupId: String) = repository.getAttendanceMeeting(groupId)
}