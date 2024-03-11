package com.example.eldiploma.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.example.eldiploma.presentation.searchStudents.SearchStudentsContent
import com.example.eldiploma.presentation.students.StudentsContent
import com.example.eldiploma.presentation.ui.theme.ElDiplomaTheme

@Composable
fun RootContent(component: RootComponent) {
    ElDiplomaTheme {
        Children(stack = component.stack) {
            when(val instance = it.instance){
                is RootComponent.Child.SearchStudents -> {
                    SearchStudentsContent(component = instance.component)
                }
                is RootComponent.Child.Students -> {
                    StudentsContent(component = instance.component)
                }
            }
        }
    }

}