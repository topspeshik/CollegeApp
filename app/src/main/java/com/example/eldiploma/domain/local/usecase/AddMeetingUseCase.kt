package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.entity.Meeting
import com.example.eldiploma.domain.local.repository.MeetingRepository
import javax.inject.Inject

class AddMeetingUseCase @Inject constructor(
    private val repository: MeetingRepository
) {
    suspend operator fun invoke(meeting: List<Meeting>) = repository.addMeeting(meeting)
}