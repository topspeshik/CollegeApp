package com.example.eldiploma.presentation.attendance

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.presentation.extenstions.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DefaultAttendanceComponent @AssistedInject constructor(
    private val storeFactory: AttendanceStoreFactory,
    @Assisted("studentGroup") private val studentGroup: StudentGroup,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : AttendanceComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(studentGroup) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect{
                when (it) {
                    AttendanceStore.Label.ClickBack -> {
                        onBackClicked()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<AttendanceStore.State> = store.stateFlow

    override fun onClickBack() {
        store.accept(AttendanceStore.Intent.ClickBack)
    }

    override fun onClickChangePresent(attendance: Attendance) {
        store.accept(AttendanceStore.Intent.ClickChangePresent(attendance))
    }

    override fun onDateChanged(date: String) {
        store.accept(AttendanceStore.Intent.ClickChangeDate(date))

    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("studentGroup") studentGroup: StudentGroup,
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultAttendanceComponent
    }
}