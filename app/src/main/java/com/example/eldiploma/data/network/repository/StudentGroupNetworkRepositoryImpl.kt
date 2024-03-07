package com.example.eldiploma.data.network.repository

import com.example.eldiploma.data.network.ApiService
import com.example.eldiploma.data.network.mapper.toEntities
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.network.repository.StudentGroupNetworkRepository
import javax.inject.Inject

class StudentGroupNetworkRepositoryImpl@Inject constructor(
    private val apiService: ApiService
)  : StudentGroupNetworkRepository {
    override suspend fun getStudentGroup(ids: String): List<StudentGroup> {
        return apiService.getStudentGroup(ids).list.toEntities()
    }
}