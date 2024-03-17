package com.example.eldiploma.domain.network.usecase

import com.example.eldiploma.domain.network.repository.MeetingNetworkRepository
import javax.inject.Inject

class GetMeetingNetworkUseCase @Inject constructor(
    private val repository: MeetingNetworkRepository
) {
    suspend operator fun invoke() = repository.getMeeting()
}