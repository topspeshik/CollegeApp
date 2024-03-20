package com.example.eldiploma.presentation.pagesClassbook.groupsRoot

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.example.eldiploma.presentation.pagesClassbook.groups.GroupsContent
import com.example.eldiploma.presentation.pagesClassbook.search.SearchContent

@Composable
fun GroupsRootContent(component: GroupsRootComponent){
    Children(stack = component.stack) {
        when(val instance = it.instance){
            is GroupsRootComponent.Child.SearchGroups -> {
                SearchContent(component = instance.component)
            }
            is GroupsRootComponent.Child.Groups -> {
               GroupsContent(component = instance.component)
            }
        }
    }
}