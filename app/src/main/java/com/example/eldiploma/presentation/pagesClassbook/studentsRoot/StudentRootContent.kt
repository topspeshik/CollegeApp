package com.example.eldiploma.presentation.pagesClassbook.studentsRoot

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.example.eldiploma.presentation.pagesClassbook.search.SearchContent
import com.example.eldiploma.presentation.pagesClassbook.students.StudentsContent

@Composable
fun StudentsRootContent(component: StudentRootComponent) {
    Children(stack = component.stack) {
        when(val instance = it.instance){
            is StudentRootComponent.Child.SearchStudents -> {
                SearchContent(component = instance.component)
            }
            is StudentRootComponent.Child.Students -> {
                StudentsContent(component = instance.component)
            }
        }
    }
}