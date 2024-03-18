package com.example.eldiploma.presentation.attendance

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.local.usecase.AddAttendanceListUseCase
import com.example.eldiploma.domain.local.usecase.AddAttendanceUseCase
import com.example.eldiploma.domain.local.usecase.GetAttendanceWithMeetingUseCase
import com.example.eldiploma.domain.network.usecase.UpdateAttendanceUseCase
import com.example.eldiploma.presentation.attendance.AttendanceStore.Intent
import com.example.eldiploma.presentation.attendance.AttendanceStore.Label
import com.example.eldiploma.presentation.attendance.AttendanceStore.State
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

interface AttendanceStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickBack : Intent

        data class ClickChangePresent(val attendance: Attendance) : Intent

        data class ClickChangeDate(val date: String) : Intent
    }

    data class State(
        val studentAttendance: List<Attendance>,
        val date: String
    )


    sealed interface Label {
        data object ClickBack : Label
    }
}

class AttendanceStoreFactory @Inject  constructor(
    private val storeFactory: StoreFactory,
    private val getAttendanceWithMeetingUseCase: GetAttendanceWithMeetingUseCase,
    private val addAttendanceUseCase: AddAttendanceUseCase,
    private val updateAttendanceUseCase: UpdateAttendanceUseCase
) {

    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    lateinit var studentGroupId: String

    fun create(studentGroup: StudentGroup): AttendanceStore =
        object : AttendanceStore, Store<Intent, State, Label> by storeFactory.create(
            name = "AttendanceStore",
            initialState = State(
                studentAttendance = listOf(),
                date = formattedDate
            ),
            bootstrapper = BootstrapperImpl(studentGroup),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {

        }

    private sealed interface Action {
        data class AttendanceLoaded(val attList: List<Attendance>) : Action

    }

    private sealed interface Msg {
        data class AttendanceLoaded(val attList: List<Attendance>) : Msg

        data class DateChanged(val date: String) : Msg

    }

    private inner class BootstrapperImpl(
        private val studentGroup: StudentGroup
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            studentGroupId = studentGroup.groupId
            scope.launch {
                getAttendanceWithMeetingUseCase(studentGroup.groupId, formattedDate).collect{
                    dispatch(Action.AttendanceLoaded(it))
                }
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when(intent){
                is Intent.ClickBack -> {
                    publish(Label.ClickBack)
                }

                is Intent.ClickChangePresent -> {
                    scope.launch {
                        val updatedPresent = JsonObject().apply {
                            addProperty("isPresent", !intent.attendance.isPresent!!)
                        }
                        updateAttendanceUseCase(intent.attendance.id, updatedPresent)
                        addAttendanceUseCase(intent.attendance.copy(isPresent = !intent.attendance.isPresent!!))
                    }

                }

                is Intent.ClickChangeDate -> {
                    scope.launch {
                        getAttendanceWithMeetingUseCase(studentGroupId, intent.date).collect {
                            Log.d("checkGroups", studentGroupId)
                            dispatch(Msg.AttendanceLoaded(it))
                            dispatch(Msg.DateChanged(intent.date))
                        }
                    }
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when(action){
                is Action.AttendanceLoaded ->{
                    val attList = action.attList
                    dispatch(Msg.AttendanceLoaded(attList))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when(msg){
            is Msg.AttendanceLoaded -> {
                copy(studentAttendance = msg.attList)

            }

            is Msg.DateChanged -> {
                copy(date = msg.date)
            }
        }
    }
}
