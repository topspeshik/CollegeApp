package com.example.eldiploma.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.presentation.searchStudents.SearchStudentsComponent
import com.example.eldiploma.presentation.students.StudentsComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child{
        data class Students(val component: StudentsComponent) : Child

        data class SearchStudents(val component: SearchStudentsComponent) : Child


    }
}