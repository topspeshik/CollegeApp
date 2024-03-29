package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.local.repository.StudentGroupRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetStudentGroupsUseCase @Inject constructor(
    private val repository: StudentGroupRepository
) {
    operator fun invoke() = repository.getStudentGroups()

}