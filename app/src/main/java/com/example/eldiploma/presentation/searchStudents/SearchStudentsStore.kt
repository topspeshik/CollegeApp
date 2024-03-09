package com.example.eldiploma.presentation.searchStudents

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.local.usecase.GetStudentsByName
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

        data class ClickStudent(val student: Student): Intent
    }

    data class State(
        val searchQuery: String,
        val searchState: SearchState
    ){

        sealed interface SearchState{
            data object Initial : SearchState

            data object EmptyResult: SearchState

            data object Loading: SearchState


            data class SuccessLoaded(val students: List<Student>): SearchState
        }
    }

    sealed interface Label {

        data object ClickBack: Label

        data class OpenStudent(val student: Student): Label
    }
}

 class SearchStudentsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getStudentsByName: GetStudentsByName
) {

    fun create(): SearchStudentsStore =
        object : SearchStudentsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "SearchStudentsStore",
            initialState = State(
                searchQuery = "",
                searchState = State.SearchState.Initial
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
        data class ChangeSearchQuery(val query: String) : Msg

        data object LoadingSearchResult: Msg

        data class SearchResultLoaded(val students: List<Student>) : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

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
                        dispatch(Msg.LoadingSearchResult)
                        val students = getStudentsByName(getState().searchQuery)
                        dispatch(Msg.SearchResultLoaded(students))
                    }

                }
                is Intent.ClickStudent -> {
                    publish(Label.OpenStudent(intent.student))
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
