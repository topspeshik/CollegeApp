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
import javax.inject.Inject

interface AttendanceStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickBack : Intent

        data class ClickChangePresent(val attendance: Attendance) : Intent
    }

    data class State(
        val studentAttendance: List<Attendance>,
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

    fun create(studentGroup: StudentGroup): AttendanceStore =
        object : AttendanceStore, Store<Intent, State, Label> by storeFactory.create(
            name = "AttendanceStore",
            initialState = State(
                studentAttendance = listOf()
            ),
            bootstrapper = BootstrapperImpl(studentGroup),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class AttendanceLoaded(val attList: List<Attendance>) : Action

    }

    private sealed interface Msg {
        data class AttendanceLoaded(val attList: List<Attendance>) : Msg

    }

    private inner class BootstrapperImpl(
        private val studentGroup: StudentGroup
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                Log.d("checkStudnetGroup", studentGroup.groupId.toString())
                getAttendanceWithMeetingUseCase(studentGroup.groupId).collect{
                    Log.d("checkStudnetGroup", it.map { it.studentName }.toString())

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
            is Msg.AttendanceLoaded -> copy(studentAttendance = msg.attList)
        }
    }
}
