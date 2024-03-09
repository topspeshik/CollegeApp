package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.local.repository.StudentRepository
import javax.inject.Inject

class GetStudentsByName @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(search: String) = repository.getStudentsByName(search)
}