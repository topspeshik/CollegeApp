package com.example.eldiploma.domain.local.repository

import com.example.eldiploma.domain.entity.Meeting

interface MeetingRepository {

    suspend fun getMeeting(): List<Meeting>

    suspend fun addMeeting(meeting: List<Meeting>)

}