package com.example.eldiploma.presentation.pagesClassbook.students

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.presentation.extensions.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultStudentsComponent @AssistedInject constructor(
    private val studentStoreFactory: StudentStoreFactory,
    @Assisted private val onSearchClicked: () -> Unit,
    @Assisted private val onStudentClicked: (StudentGroup) -> Unit,
    @Assisted componentContext: ComponentContext
) : StudentsComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore { studentStoreFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect{
                when(it){
                    StudentStore.Label.ClickSearch -> {
                        onSearchClicked()
                    }
                    is StudentStore.Label.StudentClick -> {
                        onStudentClicked(it.studentGroup)
                    }
                }
            }
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<StudentStore.State> = store.stateFlow

    override fun onClickSearch() {
        store.accept(StudentStore.Intent.ClickSearch)
    }

    override fun onStudentClick(studentGroup: StudentGroup) {
        store.accept(StudentStore.Intent.StudentClick(studentGroup))
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted onSearchClicked: () -> Unit,
            @Assisted onStudentClicked: (StudentGroup) -> Unit,
            @Assisted componentContext: ComponentContext
        ): DefaultStudentsComponent
    }
}