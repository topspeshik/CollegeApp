package com.example.eldiploma.data.network.repository

import com.example.eldiploma.data.network.ApiFactory
import com.example.eldiploma.data.network.ApiService
import com.example.eldiploma.data.network.mapper.toDto
import com.example.eldiploma.data.network.mapper.toEntities
import com.example.eldiploma.data.network.mapper.toEntity
import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.network.repository.StudentNetworkRepository
import javax.inject.Inject

class StudentNetworkRepositoryImpl  @Inject constructor(
    private val apiService: ApiService
) : StudentNetworkRepository {
    override suspend fun getStudents(ids: String): List<Student> {
        val students = apiService.getStudents(ids)
        return students.list.toEntities()
    }

    override suspend fun addStudent(student: Student) = apiService.addStudent(student.toDto())
}