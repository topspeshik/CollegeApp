package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.local.repository.StudentGroupRepository
import javax.inject.Inject

class GetStudentGroupsUseCase @Inject constructor(
    private val repository: StudentGroupRepository
) {
    operator fun invoke() = repository.getStudentGroups()
}