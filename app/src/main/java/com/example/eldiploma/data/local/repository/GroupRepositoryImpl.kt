package com.example.eldiploma.data.local.repository

import com.example.eldiploma.data.local.dao.GroupDao
import com.example.eldiploma.data.local.dao.StudentGroupDao
import com.example.eldiploma.data.mapper.toDbModel
import com.example.eldiploma.data.mapper.toEntities
import com.example.eldiploma.domain.entity.Group
import com.example.eldiploma.domain.local.repository.GroupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupDao: GroupDao
) : GroupRepository {
    override fun getGroups(): Flow<List<Group>> {
        return groupDao.getGroups().map { it. toEntities() }
    }

    override suspend fun addGroups(groups: List<Group>) {
        groupDao.addGroups(groups.map{it.toDbModel()})
    }
}