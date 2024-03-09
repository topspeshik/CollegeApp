package com.example.eldiploma.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.eldiploma.data.local.model.StudentDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM students")
    fun getStudents(): Flow<List<StudentDbModel>>

    @Upsert
    suspend fun addStudent(studentDbModel: StudentDbModel)

    @Upsert
    suspend fun addStudents(studentDbModel: List<StudentDbModel>)

    @Query("SELECT * FROM students WHERE name LIKE :search OR lastname LIKE :search")
    suspend fun getStudentsByName(search: String): List<StudentDbModel>
}