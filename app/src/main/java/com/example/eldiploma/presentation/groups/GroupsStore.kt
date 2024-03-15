package com.example.eldiploma.presentation.groups

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.local.usecase.GetStudentGroupsUseCase
import com.example.eldiploma.presentation.groups.GroupsStore.Intent
import com.example.eldiploma.presentation.groups.GroupsStore.State
import com.example.eldiploma.presentation.groups.GroupsStore.Label
import kotlinx.coroutines.launch
import javax.inject.Inject


interface GroupsStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickSearch: Intent

        data class GroupClick(val studentGroup: StudentGroup) : Intent
    }
    data class State(
        val groupsList: List<StudentGroup>
    )

    sealed interface Label {
        data object ClickSearch: Label

        data class GroupClick(val studentGroup: StudentGroup) : Label
    }
}

class GroupStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getStudentGroupsUseCase: GetStudentGroupsUseCase
) {

    fun create(): GroupsStore =
        object : GroupsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "GroupsStore",
            initialState = State(listOf()),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data class GroupsLoaded(val groupsList: List<StudentGroup>) : Action
    }

    private sealed interface Msg {

        data class GroupsLoaded(val groupsList: List<StudentGroup>) : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getStudentGroupsUseCase().collect{
                    val groupedByGroupId = it.groupBy { it.groupId }
                    val modifiedGroups = groupedByGroupId.flatMap { (id, students) ->
                        val studentss= mutableListOf<StudentGroup>()
                        studentss.add(students[0].copy(name = "Количество учеников = ${students.size}"))
                        studentss
                    }
                    dispatch(Action.GroupsLoaded(modifiedGroups))
                }
            }
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when(intent){
                is Intent.ClickSearch -> {
                    publish(Label.ClickSearch)
                }
                is Intent.GroupClick -> {
                    publish(Label.GroupClick(intent.studentGroup))
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when(action){
                is Action.GroupsLoaded ->{
                    val studentsList = action.groupsList
                    dispatch(Msg.GroupsLoaded(studentsList))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when(msg){
            is Msg.GroupsLoaded -> copy(groupsList = msg.groupsList)
        }
    }
}
