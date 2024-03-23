package com.example.eldiploma.domain.network.repository

import com.example.eldiploma.domain.entity.Teacher

interface TeacherNetworkRepository {
    suspend fun getTeachers(): List<Teacher>

}