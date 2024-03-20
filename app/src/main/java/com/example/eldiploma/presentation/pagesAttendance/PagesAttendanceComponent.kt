package com.example.eldiploma.presentation.pagesAttendance

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.presentation.pagesAttendance.attendance.AttendanceComponent
import com.example.eldiploma.presentation.pagesAttendance.attendanceList.AttendanceListComponent

interface PagesAttendanceComponent {

    @OptIn(ExperimentalDecomposeApi::class)
    val pages: Value<ChildPages<*, Child>>

    fun selectPage(index: Int)
    fun selectNext()
    fun selectPrev()

    sealed interface Child{
        data class AttendanceList(val component: AttendanceListComponent) : Child

        data class Attendance(val component: AttendanceComponent) : Child


    }
}