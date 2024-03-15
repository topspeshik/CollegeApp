package com.example.eldiploma.presentation.searchStudents

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.domain.local.usecase.GetStudentGroupsByGroupNameUseCase
import com.example.eldiploma.domain.local.usecase.GetStudentGroupsByNameUseCase
import com.example.eldiploma.presentation.searchStudents.SearchStudentsStore.Intent
import com.example.eldiploma.presentation.searchStudents.SearchStudentsStore.Label
import com.example.eldiploma.presentation.searchStudents.SearchStudentsStore.State
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


interface SearchStudentsStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class ChangeSearchQuery(val query: String) : Intent

        data object ClickBack : Intent

        data object ClickSearch: Intent

        data class ClickStudent(val studentGroup: StudentGroup): Intent

        data class ClickGroup(val studentGroup: StudentGroup): Intent
    }

    data class State(
        val searchQuery: String,
        val searchState: SearchState,
        val openReason: OpenReason
    ){

        sealed interface SearchState{
            data object Initial : SearchState

            data object EmptyResult: SearchState

            data object Loading: SearchState


            data class SuccessLoaded(val students: List<StudentGroup>): SearchState
        }
    }

    sealed interface Label {

        data object ClickBack: Label

        data class OpenStudent(val studentGroup: StudentGroup): Label

        data class OpenGroup(val studentGroup: StudentGroup): Label
    }
}

 class SearchStudentsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getStudentsByName: GetStudentGroupsByNameUseCase,
    private val getGroupsByGroupName: GetStudentGroupsByGroupNameUseCase
) {

    fun create(openReason: OpenReason): SearchStudentsStore =
        object : SearchStudentsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "SearchStudentsStore",
            initialState = State(
                searchQuery = "",
                searchState = State.SearchState.Initial,
                openReason = openReason
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = {ExecutorImpl(openReason)},
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
        data class ChangeSearchQuery(val query: String) : Msg

        data object LoadingSearchResult: Msg

        data class SearchResultLoaded(val students: List<StudentGroup>) : Msg
    }

    private class BootstrapperImpl() : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private inner class ExecutorImpl(private val openReason: OpenReason) : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        private var searchJob : Job? = null
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when(intent){
                is Intent.ChangeSearchQuery -> {
                    dispatch(Msg.ChangeSearchQuery(intent.query))
                }
                Intent.ClickBack -> {
                    publish(Label.ClickBack)
                }
                Intent.ClickSearch -> {
                    searchJob?.cancel()
                    searchJob = scope.launch {
                        when (getState().openReason){
                            OpenReason.StudentSearch -> {
                                dispatch(Msg.LoadingSearchResult)
                                val students = getStudentsByName(getState().searchQuery)
                                val groupedByStudentId = students.groupBy { it.studentId }
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
                                dispatch(Msg.SearchResultLoaded(students))
                            }
                            OpenReason.GroupSearch -> {
                                dispatch(Msg.LoadingSearchResult)
                                val groups = getGroupsByGroupName(getState().searchQuery)
                                val groupedByGroupId = groups.groupBy { it.groupId }
                                val modifiedGroups = groupedByGroupId.flatMap { (id, students) ->
                                    val studentss= mutableListOf<StudentGroup>()
                                    studentss.add(students[0].copy(name = "Количество учеников = ${students.size}"))
                                    studentss
                                }
                                dispatch(Msg.SearchResultLoaded(modifiedGroups))
                            }
                        }

                    }

                }
                is Intent.ClickStudent -> {
                    publish(Label.OpenStudent(intent.studentGroup))
                }

                is Intent.ClickGroup -> {
                    publish(Label.OpenGroup(intent.studentGroup))

                }
            }
        }


    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.ChangeSearchQuery -> {
                    copy(searchQuery = msg.query)
                }
                Msg.LoadingSearchResult -> {
                    copy(searchState = State.SearchState.Loading)
                }
                is Msg.SearchResultLoaded -> {
                    val searchState = if(msg.students.isEmpty())
                        State.SearchState.EmptyResult
                    else
                        State.SearchState.SuccessLoaded(msg.students)
                    copy(searchState = searchState)

                }
            }
    }
}
