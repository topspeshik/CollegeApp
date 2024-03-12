package com.example.eldiploma.presentation.pagesClassbook

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.presentation.groups.GroupsComponent
import com.example.eldiploma.presentation.classbook.ClassbookComponent

interface PagesClassbookComponent {


    @OptIn(ExperimentalDecomposeApi::class)
    val pages: Value<ChildPages<*, Child>>

    fun selectPage(index: Int)
    fun selectNext()
    fun selectPrev()

    sealed interface Child{
        data class Students(val component: ClassbookComponent) : Child

        data class Groups(val component: GroupsComponent) : Child


    }
}