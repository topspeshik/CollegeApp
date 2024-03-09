package com.example.eldiploma.presentation.searchStudents

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.presentation.extenstions.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultSearchStudentsComponent @AssistedInject constructor(
    private val storeFactory: SearchStudentsStoreFactory,
    @Assisted private val onBackClicked: () -> Unit,
    @Assisted private val onStudentClicked: (Student) -> Unit,
    @Assisted componentContext: ComponentContext
) : SearchStudentsComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }
    private val scope = componentScope()


    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    SearchStudentsStore.Label.ClickBack -> onBackClicked()
                    is SearchStudentsStore.Label.OpenStudent -> onStudentClicked(it.student)
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<SearchStudentsStore.State> = store.stateFlow

    override fun changeSearchQuery(query: String) {
        store.accept(SearchStudentsStore.Intent.ChangeSearchQuery(query))
    }

    override fun onClickBack() {
        store.accept(SearchStudentsStore.Intent.ClickBack)
    }

    override fun onClickSearch() {
        store.accept(SearchStudentsStore.Intent.ClickSearch)

    }

    override fun onStudentClick(student: Student) {
        store.accept(SearchStudentsStore.Intent.ClickStudent(student))

    }


    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted onBackClicked: () -> Unit,
            @Assisted onStudentClicked: (Student) -> Unit,
            @Assisted componentContext: ComponentContext
        ): DefaultSearchStudentsComponent
    }
}