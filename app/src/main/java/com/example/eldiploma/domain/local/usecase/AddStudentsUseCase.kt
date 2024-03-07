package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.local.repository.StudentRepository
import javax.inject.Inject

class AddStudentsUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(students: List<Student>) = repository.addStudents(students)
}