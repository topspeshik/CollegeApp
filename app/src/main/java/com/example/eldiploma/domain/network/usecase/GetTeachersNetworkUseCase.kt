package com.example.eldiploma.domain.network.usecase

import com.example.eldiploma.domain.network.repository.TeacherNetworkRepository
import javax.inject.Inject

class GetTeachersNetworkUseCase @Inject constructor(
    private val repository: TeacherNetworkRepository
) {
    suspend operator fun invoke() = repository.getTeachers()
}