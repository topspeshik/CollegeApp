package com.example.eldiploma.data.local.repository

import com.example.eldiploma.data.local.dao.GroupDao
import com.example.eldiploma.data.local.dao.StudentGroupDao
import com.example.eldiploma.data.mapper.toDbModel
import com.example.eldiploma.data.mapper.toEntities
import com.example.eldiploma.domain.entity.Group
import com.example.eldiploma.domain.local.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl@Inject constructor(
    private val groupDao: GroupDao
) : GroupRepository {
    override suspend fun getGroups(): List<Group> {
        return groupDao.getGroups().toEntities()
    }

    override suspend fun addGroups(groups: List<Group>) {
        groupDao.addGroups(groups.map{it.toDbModel()})
    }
}