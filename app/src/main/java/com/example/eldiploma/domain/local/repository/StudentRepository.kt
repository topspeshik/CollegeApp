package com.example.eldiploma.domain.local.repository

import com.example.eldiploma.domain.entity.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {

    fun getStudents(): Flow<List<Student>>


    suspend fun addStudent(student: Student)


    fun getStudentByName(search: String): Flow<List<Student>>
}