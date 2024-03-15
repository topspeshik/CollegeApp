package com.example.eldiploma.presentation.groupsRoot

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.presentation.groups.GroupsComponent
import com.example.eldiploma.presentation.searchStudents.SearchStudentsComponent

interface GroupsRootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child{
        data class Groups(val component: GroupsComponent) : Child

        data class SearchGroups(val component: SearchStudentsComponent) : Child

    }
}