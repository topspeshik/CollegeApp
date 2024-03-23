package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.local.repository.TeacherRepository
import javax.inject.Inject

class GetTeacherUseCase @Inject constructor(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(id: String) = repository.getTeacher(id)
}