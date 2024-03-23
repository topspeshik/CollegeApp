package com.example.eldiploma.domain.local.repository


import com.example.eldiploma.domain.entity.Group
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
    fun getGroups(): Flow<List<Group>>

    suspend fun addGroups(groups: List<Group>)

}