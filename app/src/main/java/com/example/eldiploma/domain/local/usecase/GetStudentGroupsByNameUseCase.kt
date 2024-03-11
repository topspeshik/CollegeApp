package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.local.repository.StudentGroupRepository
import javax.inject.Inject

class GetStudentGroupsByNameUseCase @Inject constructor(
    private val repository: StudentGroupRepository
) {
    suspend operator fun invoke(search: String) = repository.getStudentGroupsByName(search)
}