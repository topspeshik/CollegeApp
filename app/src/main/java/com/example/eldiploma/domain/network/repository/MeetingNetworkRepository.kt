package com.example.eldiploma.domain.network.repository

import com.example.eldiploma.domain.entity.Meeting


interface MeetingNetworkRepository {
    suspend fun getMeeting(): List<Meeting>
}