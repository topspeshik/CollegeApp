package com.example.eldiploma.presentation.students

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.presentation.extenstions.componentScope
import com.example.eldiploma.presentation.searchStudents.DefaultSearchStudentsComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultStudentsComponent @Inject constructor(
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
        ): DefaultSearchStudentsComponent
    }
}