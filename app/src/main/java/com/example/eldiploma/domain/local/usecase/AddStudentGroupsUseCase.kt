package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.local.repository.StudentGroupRepository
import javax.inject.Inject

class AddStudentGroupsUseCase @Inject constructor(
    private val repository: StudentGroupRepository
) {
    suspend operator fun invoke(studentGroups: List<StudentGroup>) = repository.addStudentGroups(studentGroups)
}