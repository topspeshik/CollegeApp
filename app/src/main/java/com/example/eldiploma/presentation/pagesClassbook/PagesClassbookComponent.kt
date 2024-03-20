package com.example.eldiploma.presentation.pagesClassbook

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.presentation.pagesClassbook.studentsRoot.StudentRootComponent
import com.example.eldiploma.presentation.pagesClassbook.groupsRoot.GroupsRootComponent

interface PagesClassbookComponent {


    @OptIn(ExperimentalDecomposeApi::class)
    val pages: Value<ChildPages<*, Child>>

    fun selectPage(index: Int)
    fun selectNext()
    fun selectPrev()

    sealed interface Child{
        data class Students(val component: StudentRootComponent) : Child

        data class Groups(val component: GroupsRootComponent) : Child


    }
}