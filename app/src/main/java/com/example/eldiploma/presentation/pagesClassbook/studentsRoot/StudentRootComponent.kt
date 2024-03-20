package com.example.eldiploma.presentation.pagesClassbook.studentsRoot

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.presentation.pagesClassbook.search.SearchComponent
import com.example.eldiploma.presentation.pagesClassbook.students.StudentsComponent

interface StudentRootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child{
        data class Students(val component: StudentsComponent) : Child

        data class SearchStudents(val component: SearchComponent) : Child

    }
}