package com.example.eldiploma.presentation.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eldiploma.R
import com.example.eldiploma.domain.entity.Attendance
import kotlin.math.round

@Composable
fun AttendanceContent(component: AttendanceComponent) {
    Column {
        Header()

        val state = component.model.collectAsState()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(14.dp,0.dp,14.dp,14.dp)
        ){

            items(
                items = state.value.studentAttendance,
                key = {it.id}
            ){
                AttendanceCard(it, onPresentClick = {component.onClickChangePresent(it)})
            }
        }
    }

}


@Composable
private fun AttendanceCard(
    attendance: Attendance,
    onPresentClick: ()->Unit
){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.large
            ),
        colors = CardDefaults.cardColors(Color(0xFFFEFEFE)),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .sizeIn(minHeight = 90.dp)
                .padding(PaddingValues(24.dp, 0.dp, 0.dp, 0.dp)),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                attendance.studentName?.let {
                    Text(
                        fontSize = 20.sp,
                        text = it
                    )
                }
            }

            RoundedCornerCheckBox(attendance, onPresentClick)
        }
    }
}


@Composable
fun RoundedCornerCheckBox(
    attendance: Attendance,
    onPresentClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(36.dp)
            .border(width = 3.dp, color = Color(0xFF1197F9), shape = RoundedCornerShape(16.dp))
            .clip(CircleShape)
            .background(Color(0xFF1197F9))
            .clickable {  onPresentClick() }
            .background(if (attendance.isPresent == true) Color(0xFF1197F9) else Color.White),
    ) {
        if (attendance.isPresent == true) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White
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
            text = stringResource(R.string.Attendance),
            fontSize = 26.sp,
            color = Color.White
        )
    }
}