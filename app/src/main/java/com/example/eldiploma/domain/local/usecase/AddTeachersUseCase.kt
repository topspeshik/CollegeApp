package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.entity.Teacher
import com.example.eldiploma.domain.local.repository.TeacherRepository
import javax.inject.Inject

class AddTeachersUseCase @Inject constructor(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(teachers: List<Teacher>) = repository.addTeachers(teachers)
}