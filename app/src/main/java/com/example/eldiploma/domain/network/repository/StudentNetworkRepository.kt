package com.example.eldiploma.domain.network.repository

import com.example.eldiploma.domain.entity.Student
import kotlinx.coroutines.flow.Flow

interface StudentNetworkRepository {

    suspend fun getStudents(): List<Student>

    suspend fun addStudent(student: Student)

}