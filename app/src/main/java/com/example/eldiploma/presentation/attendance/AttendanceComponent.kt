package com.example.eldiploma.presentation.attendance

import com.example.eldiploma.domain.entity.Attendance
import kotlinx.coroutines.flow.StateFlow

interface AttendanceComponent {

    val model: StateFlow<AttendanceStore.State>

    fun onClickBack()

    fun onClickChangePresent(attendance: Attendance)

    fun onDateChanged(date: String)
}