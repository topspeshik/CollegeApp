package com.example.eldiploma.presentation.groupsRoot

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.example.eldiploma.presentation.groups.GroupsContent
import com.example.eldiploma.presentation.searchStudents.SearchStudentsContent

@Composable
fun GroupsRootContent(component: GroupsRootComponent){
    Children(stack = component.stack) {
        when(val instance = it.instance){
            is GroupsRootComponent.Child.SearchGroups -> {
                SearchStudentsContent(component = instance.component)
            }
            is GroupsRootComponent.Child.Groups -> {
               GroupsContent(component = instance.component)
            }
        }
    }
}