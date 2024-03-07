package com.example.eldiploma.domain.network.repository

import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.entity.StudentGroup

interface StudentGroupNetworkRepository {
    suspend fun getStudentGroup(ids: String): List<StudentGroup>

}