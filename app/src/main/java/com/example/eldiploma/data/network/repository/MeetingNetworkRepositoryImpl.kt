package com.example.eldiploma.data.network.repository

import com.example.eldiploma.data.network.ApiService
import com.example.eldiploma.data.network.mapper.toEntity
import com.example.eldiploma.domain.entity.Meeting
import com.example.eldiploma.domain.network.repository.MeetingNetworkRepository
import javax.inject.Inject

class MeetingNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): MeetingNetworkRepository {
    override suspend fun getMeeting(): List<Meeting> {
        return apiService.getMeeting().list.map { it.toEntity() }
    }
}