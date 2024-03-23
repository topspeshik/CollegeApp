package com.example.eldiploma.presentation.profile

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.eldiploma.domain.entity.Attendance
import com.example.eldiploma.domain.entity.Group
import com.example.eldiploma.domain.entity.Teacher
import com.example.eldiploma.domain.local.usecase.GetGroupsUseCase
import com.example.eldiploma.domain.local.usecase.GetTeacherUseCase
import com.example.eldiploma.presentation.pagesAttendance.attendance.AttendanceStoreFactory
import com.example.eldiploma.presentation.profile.ProfileStore.Intent
import com.example.eldiploma.presentation.profile.ProfileStore.Label
import com.example.eldiploma.presentation.profile.ProfileStore.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ProfileStore : Store<Intent, State, Label> {

    sealed interface Intent {
    }

    data class State(
        val teacher: Teacher,
        val groups: List<Group>
    )


    sealed interface Label {
    }
}

class ProfileStoreFactory @Inject constructor(
    private val getTeacherUseCase: GetTeacherUseCase,
    private val getGroupsUseCase: GetGroupsUseCase,
    private val storeFactory: StoreFactory
) {

    fun create(): ProfileStore =
        object : ProfileStore, Store<Intent, State, Label> by storeFactory.create(
            name = "ProfileStore",
            initialState = State(
                Teacher(
                    "",
                    firstName = null,
                    lastName = null,
                    phoneNumber = null,
                    emailAddress = null
                ),
                groups = listOf()
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class TeacherLoaded(val teacher: Teacher) : Action

        data class GroupsLoaded(val groups: List<Group>) : Action
    }

    private sealed interface Msg {
        data class TeacherLoaded(val teacher: Teacher) : Msg
        data class GroupsLoaded(val groups: List<Group>) : Msg

    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch(Dispatchers.IO) {
                val teacher = getTeacherUseCase("65e1817fa60499374")
                withContext(Dispatchers.Main){
                    dispatch(Action.TeacherLoaded(teacher))
                }
            }
            scope.launch {
                getGroupsUseCase().collect{
                    dispatch(Action.GroupsLoaded(it))

                }
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when(action){
                is Action.TeacherLoaded ->{
                    val teacher = action.teacher
                    dispatch(Msg.TeacherLoaded(teacher))
                }

                is Action.GroupsLoaded -> {
                    val groups = action.groups
                    dispatch(Msg.GroupsLoaded(groups))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when(msg) {
                is Msg.TeacherLoaded -> copy(teacher = msg.teacher)
                is Msg.GroupsLoaded -> copy(groups = msg.groups)
            }
    }
}

