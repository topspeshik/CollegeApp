package com.example.eldiploma.domain.network.usecase

import com.example.eldiploma.data.network.WhereCondition
import com.example.eldiploma.domain.network.repository.GroupNetworkRepository
import javax.inject.Inject

class GetGroupNetworkUseCase @Inject constructor(
    private val repository: GroupNetworkRepository
) {
    suspend operator fun invoke(id: String) = repository.getGroups(id)
}