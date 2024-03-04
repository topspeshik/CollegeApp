package com.example.eldiploma.domain.network.usecase

import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.network.repository.StudentNetworkRepository
import javax.inject.Inject

class AddStudentNetworkUseCase @Inject constructor(
    private val repository: StudentNetworkRepository
) {
    suspend operator fun invoke(student: Student) = repository.addStudent(student)
}