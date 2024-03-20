package com.example.eldiploma.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.eldiploma.data.local.model.AttendanceDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {
    @Query("SELECT * FROM attendance")
    fun getAttendance(): Flow<List<AttendanceDbModel>>

    @Upsert
    suspend fun addAttendanceList(attendanceDbModels: List<AttendanceDbModel>)

    @Upsert
    suspend fun addAttendance(attendanceDbModel: AttendanceDbModel)


    @Query("SELECT a.id, a.studentName, a.meetingId, a.meetingName, a.isPresent, a.studentId FROM attendance a " +
            "JOIN meeting mt ON a.meetingId = mt.id WHERE mt.groupId = :groupId AND mt.dateStart  LIKE '%' || :date || '%'")
    fun getAttendanceWithMeeting(groupId: String, date: String): Flow<List<AttendanceDbModel>>

    @Query("SELECT a.id, a.studentName, a.meetingId, a.meetingName, a.isPresent, a.studentId FROM attendance a " +
            "JOIN meeting mt ON a.meetingId = mt.id WHERE mt.groupId = :groupId")
    fun getAttendanceMeeting(groupId: String): Flow<List<AttendanceDbModel>>

}