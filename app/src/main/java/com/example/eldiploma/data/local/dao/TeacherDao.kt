package com.example.eldiploma.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.eldiploma.data.local.model.AttendanceDbModel
import com.example.eldiploma.data.local.model.TeacherDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDao {

    @Query("SELECT * FROM teachers WHERE id =:id")
    fun getTeacher(id: String): TeacherDbModel

    @Upsert
    suspend fun addTeacherList(teacherDbModel: List<TeacherDbModel>)
}