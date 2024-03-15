package com.example.eldiploma.data.local.repository

import com.example.eldiploma.data.local.dao.StudentDao
import com.example.eldiploma.data.local.dao.StudentGroupDao
import com.example.eldiploma.data.mapper.toDbModel
import com.example.eldiploma.data.mapper.toEntities
import com.example.eldiploma.data.mapper.toEntity
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.local.repository.StudentGroupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StudentGroupRepositoryImpl @Inject constructor(
    private val studentGroupDao: StudentGroupDao
): StudentGroupRepository {
    override fun getStudentGroups(): Flow<List<StudentGroup>> {
        return studentGroupDao.getStudentGroups().map { it.toEntities() }
    }

    override suspend fun addStudentGroup(studentGroup: StudentGroup) {
        studentGroupDao.addStudentGroup(studentGroup.toDbModel())
    }

    override suspend fun addStudentGroups(studentGroups: List<StudentGroup>) {
        studentGroupDao.addStudentGroups(studentGroups.map{it.toDbModel()})
    }

    override suspend fun getStudentGroupsByName(search: String): List<StudentGroup> {
        return studentGroupDao.getStudentGroupsByName(search).map { it.toEntity() }

    }

    override suspend fun getStudentGroupsByGroupName(search: String): List<StudentGroup> {
        return studentGroupDao.getStudentGroupsByGroupName(search).map { it.toEntity() }
    }
}