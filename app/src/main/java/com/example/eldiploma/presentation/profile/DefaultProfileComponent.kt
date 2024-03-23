package com.example.eldiploma.presentation.profile

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.eldiploma.presentation.extensions.componentScope
import com.example.eldiploma.presentation.pagesAttendance.attendance.DefaultAttendanceComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.StateFlow

class DefaultProfileComponent @AssistedInject constructor(
    private val storeFactory: ProfileStoreFactory,
    @Assisted componentContext: ComponentContext
) : ProfileComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore { storeFactory.create() }
    private val scope = componentScope()

    override val model: StateFlow<ProfileStore.State> = store.stateFlow

    @AssistedFactory
    interface Factory{

        fun create(
            @Assisted componentContext: ComponentContext
        ) : DefaultProfileComponent
    }
}