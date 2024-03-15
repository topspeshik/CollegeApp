package com.example.eldiploma.presentation.classbook

import android.os.Parcelable
import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.presentation.groups.DefaultGroupsComponent
import com.example.eldiploma.presentation.searchStudents.DefaultSearchStudentsComponent
import com.example.eldiploma.presentation.searchStudents.OpenReason
import com.example.eldiploma.presentation.students.DefaultStudentsComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class DefaultClassbookComponent @AssistedInject constructor(
    private val studentsComponentFactory: DefaultStudentsComponent.Factory,
    private val searchStudentsComponentFactory: DefaultSearchStudentsComponent.Factory,
    @Assisted componentContext: ComponentContext
) : ClassbookComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()
    override val stack: Value<ChildStack<*, ClassbookComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Students,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): ClassbookComponent.Child{
        return when(config){
            Config.SearchStudents -> {
                val component = searchStudentsComponentFactory.create(
                    openReason = OpenReason.StudentSearch,
                    onBackClicked = {
                        navigation.pop()
                    },
                    onStudentClicked = {

                    },
                    onGroupClicked = {

                    },
                    componentContext = componentContext
                )
                ClassbookComponent.Child.SearchStudents(component)
            }
            Config.Students -> {
                val component = studentsComponentFactory.create(
                    onSearchClicked = {
                        navigation.push(Config.SearchStudents)
                    },
                    onStudentClicked = {

                    },
                    componentContext = componentContext
                )
                ClassbookComponent.Child.Students(component)
            }
        }

    }

    sealed interface Config: Parcelable {

        @Parcelize
        data object Students: Config
        @Parcelize
        data object SearchStudents: Config

    }

    @AssistedFactory
    interface Factory{

        fun create(
            @Assisted componentContext: ComponentContext
        ) : DefaultClassbookComponent
    }
}