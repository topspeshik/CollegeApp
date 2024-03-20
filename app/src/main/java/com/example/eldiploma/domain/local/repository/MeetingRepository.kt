package com.example.eldiploma.domain.local.repository

import com.example.eldiploma.domain.entity.Meeting
import kotlinx.coroutines.flow.Flow

interface MeetingRepository {

    suspend fun getMeeting(groupId: String): Flow<List<Meeting>>

    suspend fun addMeeting(meeting: List<Meeting>)

}