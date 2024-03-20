package com.example.eldiploma.presentation.pagesClassbook.groups

import com.example.eldiploma.domain.entity.StudentGroup
import kotlinx.coroutines.flow.StateFlow

interface GroupsComponent {

    val model: StateFlow<GroupsStore.State>

    fun onClickSearch()

    fun onGroupClick(studentGroup: StudentGroup)
}