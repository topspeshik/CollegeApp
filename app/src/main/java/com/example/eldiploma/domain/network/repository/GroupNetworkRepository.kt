package com.example.eldiploma.domain.network.repository

import com.example.eldiploma.data.network.WhereCondition
import com.example.eldiploma.domain.entity.Group

interface GroupNetworkRepository {

    suspend fun getGroups(id: String): List<Group>
}