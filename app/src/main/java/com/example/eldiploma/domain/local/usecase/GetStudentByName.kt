package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.local.repository.StudentRepository
import javax.inject.Inject

class GetStudentByName @Inject constructor(
    private val repository: StudentRepository
) {
    operator fun invoke(search: String) = repository.getStudentByName(search)
}