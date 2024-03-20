package com.example.eldiploma.presentation.pagesAttendance

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.Pages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.router.pages.select
import com.arkivanov.decompose.router.pages.selectNext
import com.arkivanov.decompose.router.pages.selectPrev
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.presentation.pagesAttendance.attendance.AttendanceComponent
import com.example.eldiploma.presentation.pagesAttendance.attendance.DefaultAttendanceComponent
import com.example.eldiploma.presentation.pagesAttendance.attendanceList.DefaultAttendanceListComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class DefaultPagesAttendanceComponent @AssistedInject constructor(
    private val attendanceListComponentFactory: DefaultAttendanceListComponent.Factory,
    private val attendanceComponentFactory: DefaultAttendanceComponent.Factory,
    @Assisted("studentGroup") private val studentGroup: StudentGroup,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted componentContext: ComponentContext,
) : PagesAttendanceComponent, ComponentContext by componentContext {

    @OptIn(ExperimentalDecomposeApi::class)
    private val nav = PagesNavigation<Config>()

    @OptIn(ExperimentalDecomposeApi::class)
    override val pages: Value<ChildPages<*, PagesAttendanceComponent.Child>> =
        childPages(
            source = nav,
            initialPages = {
                Pages(
                    items = List(2) {index -> if(index==0) Config.AttendanceList else Config.Attendance},
                    selectedIndex = 0,
                )
            },
        ) { config, childComponentContext ->
            when(config){
                Config.Attendance -> {
                    PagesAttendanceComponent.Child.Attendance(attendanceComponentFactory.create(studentGroup = studentGroup,childComponentContext))
                }
                Config.AttendanceList -> {
                    PagesAttendanceComponent.Child.AttendanceList(attendanceListComponentFactory.create(
                        studentGroup = studentGroup,
                        onBackClicked = onBackClicked,
                        childComponentContext
                    ))
                }
            }
        }
    sealed interface Config: Parcelable {

        @Parcelize
        data object Attendance: Config
        @Parcelize
        data object AttendanceList: Config

    }
    @OptIn(ExperimentalDecomposeApi::class)
    override fun selectPage(index: Int) {
        nav.select(index = index)
    }

    @OptIn(ExperimentalDecomposeApi::class)
    override fun selectNext() {
        nav.selectNext()
    }

    @OptIn(ExperimentalDecomposeApi::class)
    override fun selectPrev() {
        nav.selectPrev()
    }

    @AssistedFactory
    interface Factory{

        fun create(
            @Assisted("studentGroup") studentGroup: StudentGroup,
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted componentContext: ComponentContext
        ) : DefaultPagesAttendanceComponent
    }
}
