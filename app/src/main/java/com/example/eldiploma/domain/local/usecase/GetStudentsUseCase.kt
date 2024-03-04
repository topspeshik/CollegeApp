package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.local.repository.StudentRepository
import javax.inject.Inject

class GetStudentsUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    operator fun invoke() = repository.getStudents()
}