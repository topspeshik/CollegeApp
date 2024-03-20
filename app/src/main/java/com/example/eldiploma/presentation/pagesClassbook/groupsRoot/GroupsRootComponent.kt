package com.example.eldiploma.presentation.pagesClassbook.groupsRoot

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.presentation.pagesClassbook.groups.GroupsComponent
import com.example.eldiploma.presentation.pagesClassbook.search.SearchComponent

interface GroupsRootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child{
        data class Groups(val component: GroupsComponent) : Child

        data class SearchGroups(val component: SearchComponent) : Child

    }
}