package com.example.eldiploma.data.local.repository

import com.example.eldiploma.data.local.dao.AttendanceDao
import com.example.eldiploma.data.local.dao.MeetingDao
import com.example.eldiploma.data.mapper.toDbModel
import com.example.eldiploma.data.mapper.toEntities
import com.example.eldiploma.domain.entity.Meeting
import com.example.eldiploma.domain.local.repository.MeetingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MeetingRepositoryImpl@Inject constructor(
    private val meetingDao: MeetingDao
) : MeetingRepository {
    override suspend fun getMeeting(groupId: String): Flow<List<Meeting>> {
        return meetingDao.getMeeting(groupId).map { it.toEntities() }
    }

    override suspend fun addMeeting(meeting: List<Meeting>) {
        meetingDao.addMeeting(meeting.map{it.toDbModel()})

    }
}