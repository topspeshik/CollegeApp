package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.local.repository.MeetingRepository
import javax.inject.Inject

class GetMeetingUseCase @Inject constructor(
    private val repository: MeetingRepository
) {
    suspend operator fun invoke() = repository.getMeeting()
}