package com.example.eldiploma.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.eldiploma.data.local.model.MeetingDbModel

@Dao
interface MeetingDao {
    @Query("SELECT * FROM meeting")
    fun getMeeting(): List<MeetingDbModel>

    @Upsert
    suspend fun addMeeting(meetingDbModels: List<MeetingDbModel>)
}