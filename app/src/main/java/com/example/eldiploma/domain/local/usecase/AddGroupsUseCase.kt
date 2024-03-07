package com.example.eldiploma.domain.local.usecase

import com.example.eldiploma.domain.entity.Group
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.local.repository.GroupRepository
import com.example.eldiploma.domain.local.repository.StudentGroupRepository
import javax.inject.Inject

class AddGroupsUseCase @Inject constructor(
    private val repository: GroupRepository
) {
    suspend operator fun invoke(groups: List<Group>) = repository.addGroups(groups)
}