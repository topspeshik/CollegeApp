package com.example.eldiploma.presentation.pagesClassbook.search

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

class DefaultSearchComponent @AssistedInject constructor(
    private val storeFactory: SearchStudentsStoreFactory,
    @Assisted private val openReason: OpenReason,
    @Assisted private val onBackClicked: () -> Unit,
    @Assisted private val onStudentClicked: (StudentGroup) -> Unit,
    @Assisted("onGroupClicked") private val onGroupClicked: (StudentGroup) -> Unit,
    @Assisted componentContext: ComponentContext
) : SearchComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(openReason) }
    private val scope = componentScope()


    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    SearchStore.Label.ClickBack -> onBackClicked()
                    is SearchStore.Label.OpenStudent -> onStudentClicked(it.studentGroup)
                    is SearchStore.Label.OpenGroup -> {
                        onGroupClicked(it.studentGroup)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<SearchStore.State> = store.stateFlow

    override fun changeSearchQuery(query: String) {
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))
    }

    override fun onClickBack() {
        store.accept(SearchStore.Intent.ClickBack)
    }

    override fun onClickSearch() {
        store.accept(SearchStore.Intent.ClickSearch)

    }

    override fun onStudentClick(studentGroup: StudentGroup) {
        store.accept(SearchStore.Intent.ClickStudent(studentGroup))
    }

    override fun onGroupClick(studentGroup: StudentGroup) {
        store.accept(SearchStore.Intent.ClickGroup(studentGroup))
    }


    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted openReason: OpenReason,
            @Assisted onBackClicked: () -> Unit,
            @Assisted onStudentClicked: (StudentGroup) -> Unit,
            @Assisted("onGroupClicked") onGroupClicked: (StudentGroup) -> Unit,
            @Assisted componentContext: ComponentContext
        ): DefaultSearchComponent
    }
}