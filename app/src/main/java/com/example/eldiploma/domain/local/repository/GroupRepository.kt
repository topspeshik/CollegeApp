package com.example.eldiploma.domain.local.repository


import com.example.eldiploma.domain.entity.Group

interface GroupRepository {
    suspend fun getGroups(): List<Group>

    suspend fun addGroups(groups: List<Group>)

}