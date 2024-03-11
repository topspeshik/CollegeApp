package com.example.eldiploma.domain.local.repository

import com.example.eldiploma.data.local.model.StudentGroupDbModel
import com.example.eldiploma.domain.entity.StudentGroup
import kotlinx.coroutines.flow.Flow

interface StudentGroupRepository {
    fun getStudentGroups(): Flow<List<StudentGroup>>

    suspend fun addStudentGroup(studentGroup: StudentGroup)

    suspend fun addStudentGroups(studentGroup: List<StudentGroup>)

    suspend fun getStudentGroupsByName(search: String): List<StudentGroup>

}