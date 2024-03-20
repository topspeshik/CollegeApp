package com.example.eldiploma.presentation.pagesClassbook.groups

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

class DefaultGroupsComponent  @AssistedInject constructor(
    private val groupStoreFactory: GroupStoreFactory,
    @Assisted private val onSearchClicked: () -> Unit,
    @Assisted private val onGroupClicked: (StudentGroup) -> Unit,
    @Assisted componentContext: ComponentContext
) : GroupsComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore { groupStoreFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect{
                when(it){
                    GroupsStore.Label.ClickSearch -> {
                        onSearchClicked()
                    }
                    is GroupsStore.Label.GroupClick -> {
                        onGroupClicked(it.studentGroup)
                    }
                }
            }
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<GroupsStore.State> = store.stateFlow

    override fun onClickSearch() {
        store.accept(GroupsStore.Intent.ClickSearch)
    }

    override fun onGroupClick(studentGroup: StudentGroup) {
        store.accept(GroupsStore.Intent.GroupClick(studentGroup))
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted onSearchClicked: () -> Unit,
            @Assisted onGroupClicked: (StudentGroup) -> Unit,
            @Assisted componentContext: ComponentContext
        ): DefaultGroupsComponent
    }
}