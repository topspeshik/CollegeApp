package com.example.eldiploma.presentation.classbook

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.example.eldiploma.presentation.searchStudents.SearchStudentsContent
import com.example.eldiploma.presentation.students.StudentsContent
import com.example.eldiploma.presentation.ui.theme.ElDiplomaTheme

@Composable
fun ClassbookContent(component: ClassbookComponent) {
    Children(stack = component.stack) {
        when(val instance = it.instance){
            is ClassbookComponent.Child.SearchStudents -> {
                SearchStudentsContent(component = instance.component)
            }
            is ClassbookComponent.Child.Students -> {
                StudentsContent(component = instance.component)
            }
        }
    }
}