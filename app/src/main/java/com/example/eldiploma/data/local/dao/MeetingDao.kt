package com.example.eldiploma.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.eldiploma.data.local.model.MeetingDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MeetingDao {
    @Query("SELECT * FROM meeting WHERE groupId = :groupId")
    fun getMeeting(groupId: String): Flow<List<MeetingDbModel>>

    @Upsert
    suspend fun addMeeting(meetingDbModels: List<MeetingDbModel>)
}