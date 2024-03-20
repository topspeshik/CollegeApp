package com.example.eldiploma.presentation.pagesAttendance.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*


import androidx.compose.material.*
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.entity.Meeting
import ksnd.autosizetable.AutoSizeTable
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@SuppressLint("SuspiciousIndentation")
@Composable
fun AttendanceContent (component: AttendanceComponent) {

    val colorScheme = MaterialTheme.colorScheme
    var typeIndex by remember { mutableIntStateOf(0) }
    var numMailRowIcons by remember { mutableIntStateOf(1) }
    var numMailColumnIcons by remember { mutableIntStateOf(1) }

    val state = component.model.collectAsState()
    val NUM_OF_ITEMS_IN_EACH_COLUMN = if (state.value.studentAttendance.isEmpty()) 1 else state.value.studentAttendance.size+1
    val NUM_OF_ITEMS_IN_EACH_ROW = if (state.value.meeting.isEmpty()) 1 else state.value.meeting.size+1

    // fixedTopSize to fixedStartSize
    val type = listOf(
        1 to 1,
        1 to 0,
        0 to 1,
        0 to 0,
        1 to 2,
        2 to 2,
    )



    AutoSizeTable(
        modifier = Modifier.padding(all = 8.dp).fillMaxSize(),
        outlineColor = colorScheme.outline,
        content = List(NUM_OF_ITEMS_IN_EACH_COLUMN) { columnId ->
            List(NUM_OF_ITEMS_IN_EACH_ROW) { rowId ->
                {
                    if (rowId == 0 && columnId == 0) {
                        Text(
                            text = "ФИО",
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                    else if (rowId == 0) {

                        state.value.studentAttendance[columnId-1].studentName?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(8.dp),
                                fontWeight = if (columnId < type[typeIndex].first) {
                                    FontWeight.Bold
                                } else {
                                    FontWeight.Normal
                                },
                            )
                        }
                    }
                    else if (rowId >0 && columnId == 0 ) {
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        val date = LocalDate.parse(state.value.meeting[rowId-1].dateStart, formatter)
                        val formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM"))
                        Text(
                            text = formattedDate,
                            modifier = Modifier.padding(8.dp),
                            fontWeight = if (columnId < type[typeIndex].first) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            },
                        )
                    }
                    else  {
                        CheckBox(
                            state.value.studentAttendance[columnId-1],
                            state.value.meeting[rowId-1]
                        ) {
                            component.onClickChangePresent(
                                state.value.studentAttendance[columnId - 1]
                            )
                        }
                }
                }
            }
        },
        contentAlignment = { _, _ -> Alignment.Center },
        fixedTopSize = type[typeIndex].first,
        fixedStartSize = type[typeIndex].second,
    )

}

@Composable
fun CheckBox(
    attendance: Attendance,
    meeting: Meeting,
    onPresentClick: () -> Unit
) {
    val stateButton = (attendance.meetingId == meeting.id && attendance.isPresent == true)
    Box(
        modifier = Modifier
            .padding(6.dp)
            .size(36.dp)
            .clickable { onPresentClick() }

    ) {
        if (stateButton) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(32.dp)
                ,
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color(0xFF1197F9),
            )
        }
    }
}