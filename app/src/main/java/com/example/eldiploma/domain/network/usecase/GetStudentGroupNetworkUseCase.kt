package com.example.eldiploma.domain.network.usecase

import com.example.eldiploma.data.network.dto.StudentGroupDto
import com.example.eldiploma.domain.network.repository.StudentGroupNetworkRepository
import com.example.eldiploma.domain.network.repository.StudentNetworkRepository
import javax.inject.Inject

class GetStudentGroupNetworkUseCase @Inject constructor(
    private val repository: StudentGroupNetworkRepository
) {
    suspend operator fun invoke(id: String) = repository.getStudentGroup(id)
}