package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.local.repository.GroupRepository
import com.example.eldiploma.domain.local.repository.StudentGroupRepository
import javax.inject.Inject

class GetGroupsUseCase @Inject constructor(
    private val repository: GroupRepository
) {
    suspend operator fun invoke() = repository.getGroups()
}