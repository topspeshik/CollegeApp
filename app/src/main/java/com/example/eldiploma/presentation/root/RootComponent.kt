package com.example.eldiploma.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.presentation.attendance.AttendanceComponent
import com.example.eldiploma.presentation.pagesClassbook.PagesClassbookComponent
import com.example.eldiploma.presentation.profile.ProfileComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Profile(val component: ProfileComponent) : Child()
        class Pages(val component: PagesClassbookComponent) : Child()

        class Attendance(val component: AttendanceComponent) : Child()
    }
}