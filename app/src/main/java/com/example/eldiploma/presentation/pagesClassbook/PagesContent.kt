package com.example.eldiploma.presentation.pagesClassbook

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.pages.Pages
import com.arkivanov.decompose.extensions.compose.jetpack.pages.PagesScrollAnimation
import com.example.eldiploma.presentation.classbook.ClassbookContent
import com.example.eldiploma.presentation.groups.GroupsContent


@OptIn(ExperimentalFoundationApi::class, ExperimentalDecomposeApi::class)
@Composable
fun PagesClassbookContent(component: PagesClassbookComponent){
    Box {
        Pages(
            pages = component.pages,
            onPageSelected = component::selectPage,
            modifier = Modifier.fillMaxSize(),
            scrollAnimation = PagesScrollAnimation.Default,
        ) { number, page ->
            when (page) {
                is PagesClassbookComponent.Child.Groups -> {
                    GroupsContent(component = page.component)
                }

                is PagesClassbookComponent.Child.Students -> {
                    ClassbookContent(component = page.component)
                }
            }
        }
        Row(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OutlinedButton(
                onClick = component::selectPrev,
                modifier = Modifier.size(48.dp),
                contentPadding = PaddingValues(0.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous",
                )
            }

            OutlinedButton(
                onClick = component::selectNext,
                modifier = Modifier.size(48.dp),
                contentPadding = PaddingValues(0.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next",
                )
            }
        }
    }

}