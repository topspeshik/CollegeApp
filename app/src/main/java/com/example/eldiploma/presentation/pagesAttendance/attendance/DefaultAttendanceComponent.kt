package com.example.eldiploma.presentation.pagesAttendance.attendance

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.presentation.extensions.componentScope
import com.example.eldiploma.presentation.pagesAttendance.DefaultPagesAttendanceComponent
import com.example.eldiploma.presentation.pagesAttendance.attendanceList.AttendanceListStore
import com.example.eldiploma.presentation.pagesAttendance.attendanceList.AttendanceListStoreFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class DefaultAttendanceComponent @AssistedInject constructor(
    private val storeFactory: AttendanceStoreFactory,
    @Assisted("studentGroup") private val studentGroup: StudentGroup,
    @Assisted componentContext: ComponentContext,
) : AttendanceComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(studentGroup) }
    private val scope = componentScope()


    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<AttendanceStore.State> = store.stateFlow



    override fun onClickChangePresent(attendance: Attendance) {
        store.accept(AttendanceStore.Intent.ClickChangePresent(attendance))
    }


    @AssistedFactory
    interface Factory{

        fun create(
            @Assisted("studentGroup") studentGroup: StudentGroup,
            @Assisted componentContext: ComponentContext
        ) : DefaultAttendanceComponent
    }
}