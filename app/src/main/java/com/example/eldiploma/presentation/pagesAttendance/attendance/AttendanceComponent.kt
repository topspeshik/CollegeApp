package com.example.eldiploma.presentation.pagesAttendance.attendance

import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.presentation.pagesAttendance.attendanceList.AttendanceListStore
import kotlinx.coroutines.flow.StateFlow

interface AttendanceComponent {

    val model: StateFlow<AttendanceStore.State>

    fun onClickChangePresent(attendance: Attendance)
}