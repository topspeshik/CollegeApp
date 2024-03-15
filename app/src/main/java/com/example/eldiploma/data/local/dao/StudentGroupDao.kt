package com.example.eldiploma.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.eldiploma.data.local.model.StudentDbModel
import com.example.eldiploma.data.local.model.StudentGroupDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentGroupDao {
    @Query("SELECT * FROM student_group")
    fun getStudentGroups(): Flow<List<StudentGroupDbModel>>

    @Query("SELECT * FROM student_group WHERE name LIKE '%' || :search || '%'")
    suspend fun getStudentGroupsByName(search: String): List<StudentGroupDbModel>

    @Query("SELECT * FROM student_group WHERE groupName LIKE '%' || :search || '%'")
    suspend fun getStudentGroupsByGroupName(search: String): List<StudentGroupDbModel>

    @Upsert
    suspend fun addStudentGroup(studentGroupDbModel: StudentGroupDbModel)

    @Upsert
    suspend fun addStudentGroups(studentGroupDbModels: List<StudentGroupDbModel>)

}