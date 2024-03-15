package com.example.eldiploma.presentation.attendance

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eldiploma.R
import kotlin.math.round

@Composable
fun AttendanceContent(component: AttendanceComponent) {
    Header()
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