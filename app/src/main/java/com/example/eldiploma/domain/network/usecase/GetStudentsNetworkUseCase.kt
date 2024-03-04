package com.example.eldiploma.domain.network.usecase

import com.example.eldiploma.domain.network.repository.StudentNetworkRepository
import javax.inject.Inject

class GetStudentsNetworkUseCase @Inject constructor(
    private val repository: StudentNetworkRepository
) {
    suspend operator fun invoke() = repository.getStudents()
}