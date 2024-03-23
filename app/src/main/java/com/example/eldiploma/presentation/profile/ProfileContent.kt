package com.example.eldiploma.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eldiploma.R
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.entity.Teacher
import kotlin.math.round

@Composable
fun ProfileContent(component: ProfileComponent) {

    val state = component.model.collectAsState()

    Column {
        Header()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 14.dp, top = 32.dp, end = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = stringResource(R.string.Avatar),
                modifier = Modifier.size(150.dp).align(Alignment.CenterHorizontally),
                tint = Color(0xFFD9D9D9)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 28.dp)
                    .align(Alignment.CenterHorizontally),
                text = "${state.value.teacher.firstName} ${state.value.teacher.lastName}",
                fontSize = 28.sp
            )
            state.value.teacher.phoneNumber?.let { Card("Номер телефона:", it) }

            state.value.teacher.emailAddress?.let { Card("Email: ", it) }


            val groups = state.value.groups
            if (groups.isNotEmpty()){
                var groupName = ""
                groups.forEach{
                    groupName+= "${it.name}, "
                }
                groupName = groupName.dropLast(2)
                Card("Группы: ", groupName)
            }

        }
    }

}

@Composable
private fun Card(
    text: String,
    typeText: String
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.large
            ),
        colors = CardDefaults.cardColors(Color(0xFFFEFEFE)),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 45.dp)
                .padding(PaddingValues(24.dp, 0.dp, 24.dp, 0.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                fontSize = 16.sp,
                text = text
            )


            Text(
                fontSize = 16.sp,
                text = typeText
            )
            }
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
            text = stringResource(R.string.Teacher),
            fontSize = 26.sp,
            color = Color.White
        )
    }
}