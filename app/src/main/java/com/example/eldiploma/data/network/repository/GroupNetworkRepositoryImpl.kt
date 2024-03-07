package com.example.eldiploma.data.network.repository

import com.example.eldiploma.data.network.ApiService
import com.example.eldiploma.data.network.WhereCondition
import com.example.eldiploma.data.network.mapper.toEntity
import com.example.eldiploma.domain.entity.Group
import com.example.eldiploma.domain.network.repository.GroupNetworkRepository
import javax.inject.Inject

class GroupNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GroupNetworkRepository {
    override suspend fun getGroups(id: String): List<Group> {
        return apiService.getGroups(id).list.map { it.toEntity() }
    }
}