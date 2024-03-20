package com.example.eldiploma.presentation.pagesAttendance.attendanceList

import com.example.eldiploma.domain.entity.Attendance
import kotlinx.coroutines.flow.StateFlow

interface AttendanceListComponent {

    val model: StateFlow<AttendanceListStore.State>

    fun onClickBack()

    fun onClickChangePresent(attendance: Attendance)

    fun onDateChanged(date: String)
}