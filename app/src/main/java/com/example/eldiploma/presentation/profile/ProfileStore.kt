package com.example.eldiploma.presentation.profile

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.eldiploma.domain.entity.Teacher
import com.example.eldiploma.presentation.profile.ProfileStore.Intent
import com.example.eldiploma.presentation.profile.ProfileStore.Label
import com.example.eldiploma.presentation.profile.ProfileStore.State
import javax.inject.Inject

interface ProfileStore : Store<Intent, State, Label> {

    sealed interface Intent {
    }

    data class State( val studentAttendance: Teacher)

    sealed interface Label {
    }
}

class ProfileStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory
) {

    fun create(): ProfileStore =
        object : ProfileStore, Store<Intent, State, Label> by storeFactory.create(
            name = "ProfileStore",
            initialState = State(Teacher("12",
                firstName = null,
                lastName = null,
                phoneNumber = null
            )),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
        }

        override fun executeAction(action: Action, getState: () -> State) {
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(message: Msg): State = State(
            Teacher(
                id = "",
                firstName = null,
                lastName = null,
                phoneNumber = null
            )
        )
    }
}
