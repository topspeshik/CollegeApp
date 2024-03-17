package com.example.eldiploma.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.eldiploma.data.local.model.AttendanceDbModel

@Dao
interface AttendanceDao {
    @Query("SELECT * FROM attendance")
    fun getAttendance(): List<AttendanceDbModel>

    @Upsert
    suspend fun addAttendance(attendanceDbModels: List<AttendanceDbModel>)
}