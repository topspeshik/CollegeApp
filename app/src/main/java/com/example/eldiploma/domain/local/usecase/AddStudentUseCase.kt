package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.local.repository.StudentRepository
import javax.inject.Inject

class AddStudentUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(student: Student) = repository.addStudent(student)
}