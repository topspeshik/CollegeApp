package com.example.eldiploma.presentation.pagesAttendance.attendance

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.entity.Meeting
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.local.usecase.AddAttendanceUseCase
import com.example.eldiploma.domain.local.usecase.GetAttendanceMeetingUseCase
import com.example.eldiploma.domain.local.usecase.GetAttendanceUseCase
import com.example.eldiploma.domain.local.usecase.GetAttendanceWithMeetingUseCase
import com.example.eldiploma.domain.local.usecase.GetMeetingUseCase
import com.example.eldiploma.domain.network.usecase.UpdateAttendanceUseCase
import com.example.eldiploma.presentation.pagesAttendance.attendance.AttendanceStore.Intent
import com.example.eldiploma.presentation.pagesAttendance.attendance.AttendanceStore.Label
import com.example.eldiploma.presentation.pagesAttendance.attendance.AttendanceStore.State
import com.example.eldiploma.presentation.pagesAttendance.attendanceList.AttendanceListStoreFactory
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

interface AttendanceStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class ClickChangePresent(val attendance: Attendance) : Intent
    }

    data class State(
        val studentAttendance: List<Attendance>,
        val meeting: List<Meeting>,
    )

    sealed interface Label {
    }
}

class AttendanceStoreFactory @Inject constructor(
    private val getMeetingUseCase: GetMeetingUseCase,
    private val getAttendanceMeetingUseCase: GetAttendanceMeetingUseCase,
    private val updateAttendanceUseCase: UpdateAttendanceUseCase,
    private val getAttendanceUseCase: GetAttendanceUseCase,
    private val addAttendanceUseCase: AddAttendanceUseCase,
    private val storeFactory: StoreFactory
) {



    fun create(studentGroup: StudentGroup): AttendanceStore =
        object : AttendanceStore, Store<Intent, State, Label> by storeFactory.create(
            name = "AttendanceStore",
            initialState = State(
                listOf(),
                listOf()
            ),
            bootstrapper = BootstrapperImpl(studentGroup),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class AttendanceLoaded(val attList: List<Attendance>) : Action

        data class MeetingLoaded(val meetingList: List<Meeting>) : Action
    }

    private sealed interface Msg {
        data class AttendanceLoaded(val attList: List<Attendance>) : Msg

        data class MeetingLoaded(val meetingList: List<Meeting>) : Msg
    }

    private inner class BootstrapperImpl(private val studentGroup: StudentGroup) : CoroutineBootstrapper<Action>() {

        lateinit var studentGroupId: String
        override fun invoke() {
            studentGroupId = studentGroup.groupId
            scope.launch {
                getAttendanceMeetingUseCase(studentGroupId).collect{
                    dispatch(Action.AttendanceLoaded(it))
                }
            }
            scope.launch {
                getMeetingUseCase(studentGroupId).collect{
                    dispatch(Action.MeetingLoaded(it))
                }
            }

        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when(intent){
                is Intent.ClickChangePresent -> {
                    scope.launch {
                        val updatedPresent = JsonObject().apply {
                            addProperty("isPresent", !intent.attendance.isPresent!!)
                        }
                        updateAttendanceUseCase(intent.attendance.id, updatedPresent)
                        addAttendanceUseCase(intent.attendance.copy(isPresent = !intent.attendance.isPresent!!))
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

                is Action.MeetingLoaded -> {
                    dispatch(Msg.MeetingLoaded(action.meetingList))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.AttendanceLoaded -> {
                    copy(studentAttendance = msg.attList)
                }

                is Msg.MeetingLoaded -> {
                    copy(meeting = msg.meetingList)
                }
            }
    }
}
