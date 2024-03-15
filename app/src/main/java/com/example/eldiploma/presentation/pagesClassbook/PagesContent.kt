package com.example.eldiploma.presentation.pagesClassbook

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.pages.Pages
import com.arkivanov.decompose.extensions.compose.jetpack.pages.PagesScrollAnimation
import com.example.eldiploma.R
import com.example.eldiploma.presentation.classbook.ClassbookContent
import com.example.eldiploma.presentation.groups.GroupsContent
import com.example.eldiploma.presentation.groupsRoot.GroupsRootContent
import kotlin.math.round


@OptIn(ExperimentalFoundationApi::class, ExperimentalDecomposeApi::class)
@Composable
fun PagesClassbookContent(component: PagesClassbookComponent) {

    Column {

        val colorPicked = remember {
            mutableStateOf(Color(0xFF1197F9))
        }
        val colorUnpicked = remember {
            mutableStateOf(Color(0xFF8A8686))
        }
        Column {
            Header()
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 12.dp, end = 12.dp, top = 30.dp)

            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)

                ) {
                    Text(
                        text = stringResource(R.string.Groups),
                        color = colorPicked.value,
                        modifier = Modifier
                            .clickable(onClick = component::selectPrev)
                            .align(Alignment.CenterHorizontally)
                    )
                    Divider(color = colorPicked.value, thickness = 2.dp)
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)


                ) {
                    Text(
                        text = stringResource(R.string.Students),
                        color = colorUnpicked.value,
                        modifier = Modifier
                            .clickable(onClick = component::selectNext)
                            .align(Alignment.CenterHorizontally)

                    )
                    Divider(color = colorUnpicked.value, thickness = 2.dp)
                }
            }
        }

        Pages(
            pages = component.pages,
            onPageSelected = component::selectPage,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp),
            scrollAnimation = PagesScrollAnimation.Default,
        ) { _, page ->
            when (page) {
                is PagesClassbookComponent.Child.Groups -> {
                    changeColor(1, colorPicked, colorUnpicked)
                    GroupsRootContent(component = page.component)
                }

                is PagesClassbookComponent.Child.Students -> {
                    changeColor(2, colorPicked, colorUnpicked)
                    ClassbookContent(component = page.component)
                }
            }
        }

    }

}

fun changeColor(type: Int, colorPicked: MutableState<Color>, colorUnpicked: MutableState<Color>) {
    if (type == 1) {
        colorPicked.value = Color(0xFF1197F9)
        colorUnpicked.value = Color(0xFF8A8686)

    } else {
        colorPicked.value = Color(0xFF8A8686)
        colorUnpicked.value = Color(0xFF1197F9)
    }
}


@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .drawBehind {
                drawCircle(
                    color = Color(0xFF1197F9),
                    center = Offset(
                        x = center.x,
                        y = round(center.y - size.height * 10.9).toFloat()
                    ),
                    radius = size.maxDimension * 2
                )
            }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.Classbook),
            fontSize = 26.sp,
            color = Color.White
        )
    }
}