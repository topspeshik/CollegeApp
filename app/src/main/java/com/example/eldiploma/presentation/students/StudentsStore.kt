package com.example.eldiploma.presentation.students

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.local.usecase.GetStudentGroupsUseCase
import com.example.eldiploma.presentation.students.StudentStore.Intent
import com.example.eldiploma.presentation.students.StudentStore.Label
import com.example.eldiploma.presentation.students.StudentStore.State
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

interface StudentStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickSearch: Intent

        data class StudentClick(val studentGroup: StudentGroup) : Intent
    }

    data class State(
        val studentsList: List<StudentGroup>
    )


    sealed interface Label {
        data object ClickSearch: Label

        data class StudentClick(val studentGroup: StudentGroup) : Label
    }
}

class StudentStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getStudentGroupsUseCase: GetStudentGroupsUseCase
) {

    fun create(): StudentStore =
        object : StudentStore, Store<Intent, State, Label> by storeFactory.create(
            name = "StudentStore",
            initialState = State(listOf()),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data class StudentsLoaded(val studentsList: List<StudentGroup>) : Action
    }

    private sealed interface Msg {

        data class StudentsLoaded(val studentsList: List<StudentGroup>) : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getStudentGroupsUseCase().collect{
                    val groupedByStudentId = it.groupBy { it.studentId }
                    val modifiedGroups = groupedByStudentId.flatMap { (id, groups) ->
                        val groupps = mutableListOf<StudentGroup>()
                        var groupsName : String = ""
                        if (groups.size >1){
                            groups.forEach{
                                groupsName+= "${it.groupName}, "
                            }
                            groupsName = groupsName.dropLast(2)
                            groupps.add(groups[0].copy(groupName = groupsName))
                        }
                        else{
                            groupps.add(groups[0])
                        }
                        groupps


                    }
                    dispatch(Action.StudentsLoaded(modifiedGroups))
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
                is Intent.StudentClick -> {
                    publish(Label.StudentClick(intent.studentGroup))
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when(action){
                is Action.StudentsLoaded ->{
                    val studentsList = action.studentsList
                    dispatch(Msg.StudentsLoaded(studentsList))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when(msg){
            is Msg.StudentsLoaded -> copy(studentsList = msg.studentsList)
        }
    }
}
