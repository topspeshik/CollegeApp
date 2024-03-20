package com.example.eldiploma.presentation.pagesAttendance.attendanceList

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.presentation.extensions.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultAttendanceListComponent @AssistedInject constructor(
    private val storeFactory: AttendanceListStoreFactory,
    @Assisted("studentGroup") private val studentGroup: StudentGroup,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : AttendanceListComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(studentGroup) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect{
                when (it) {
                    AttendanceListStore.Label.ClickBack -> {
                        onBackClicked()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<AttendanceListStore.State> = store.stateFlow

    override fun onClickBack() {
        store.accept(AttendanceListStore.Intent.ClickBack)
    }

    override fun onClickChangePresent(attendance: Attendance) {
        store.accept(AttendanceListStore.Intent.ClickChangePresent(attendance))
    }

    override fun onDateChanged(date: String) {
        store.accept(AttendanceListStore.Intent.ClickChangeDate(date))

    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("studentGroup") studentGroup: StudentGroup,
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultAttendanceListComponent
    }
}