package com.example.eldiploma.data.network.repository

import com.example.eldiploma.data.network.ApiService
import com.example.eldiploma.data.network.mapper.toEntities
import com.example.eldiploma.domain.entity.Teacher
import com.example.eldiploma.domain.network.repository.TeacherNetworkRepository
import javax.inject.Inject

class TeacherNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TeacherNetworkRepository {
    override suspend fun getTeachers(): List<Teacher> {
        val students = apiService.getTeachers()
        return students.list.toEntities()
    }
}