package com.example.eldiploma.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.example.eldiploma.presentation.pagesClassbook.PagesClassbookContent
import com.example.eldiploma.presentation.profile.ProfileContent
import com.example.eldiploma.presentation.ui.theme.ElDiplomaTheme

@Composable
fun RootContent(component: RootComponent) {
    ElDiplomaTheme {
        Children(stack = component.stack) {
            when(val instance = it.instance){
                is RootComponent.Child.Pages -> {
                    PagesClassbookContent(component = instance.component)
                }
                is RootComponent.Child.Profile -> {
                    ProfileContent(component = instance.component)
                }
            }
        }
    }

}