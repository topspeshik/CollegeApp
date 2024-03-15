package com.example.eldiploma.presentation.groupsRoot

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.presentation.groups.DefaultGroupsComponent
import com.example.eldiploma.presentation.searchStudents.DefaultSearchStudentsComponent
import com.example.eldiploma.presentation.searchStudents.OpenReason
import com.example.eldiploma.presentation.students.DefaultStudentsComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class DefaultGroupsRootComponent @AssistedInject constructor(
    private val groupsComponentFactory: DefaultGroupsComponent.Factory,
    private val searchStudentsComponentFactory: DefaultSearchStudentsComponent.Factory,
    @Assisted componentContext: ComponentContext
) : GroupsRootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()
    override val stack: Value<ChildStack<*, GroupsRootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Groups,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): GroupsRootComponent.Child {
        return when (config) {
            Config.SearchGroups -> {
                val component = searchStudentsComponentFactory.create(
                    openReason = OpenReason.GroupSearch,
                    onBackClicked = {
                        navigation.pop()
                    },
                    onStudentClicked = {

                    },
                    onGroupClicked = {

                    },
                    componentContext = componentContext
                )
                GroupsRootComponent.Child.SearchGroups(component)
            }

            Config.Groups -> {
                val component = groupsComponentFactory.create(
                    onSearchClicked = {
                        navigation.push(Config.SearchGroups)
                    },
                    onGroupClicked = {

                    },
                    componentContext = componentContext
                )
                GroupsRootComponent.Child.Groups(component)
            }

        }

    }

    sealed interface Config : Parcelable {

        @Parcelize
        data object Groups : Config

        @Parcelize
        data object SearchGroups : Config


    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted componentContext: ComponentContext
        ): DefaultGroupsRootComponent
    }
}
