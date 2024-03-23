package com.example.eldiploma.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.presentation.pagesAttendance.DefaultPagesAttendanceComponent
import com.example.eldiploma.presentation.pagesAttendance.attendanceList.DefaultAttendanceListComponent
import com.example.eldiploma.presentation.pagesClassbook.DefaultPagesClassbookComponent
import com.example.eldiploma.presentation.profile.DefaultProfileComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class DefaultRootComponent @AssistedInject constructor(
    private val pagesFactoryComponent: DefaultPagesClassbookComponent.Factory,
    private val attendanceFactoryComponent: DefaultPagesAttendanceComponent.Factory,
    @Assisted componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()
    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Classbook,
        handleBackButton = true,
        childFactory = ::child
    )

    override fun onClickPages() {
        navigation.bringToFront(Config.Classbook)
    }

    override fun onClickProfile() {
        navigation.bringToFront(Config.Profile)
    }

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child{
        return when(config){
            is Config.Attendance -> {
                val component = attendanceFactoryComponent.create(
                    studentGroup = config.studentGroup ,
                    onBackClicked = { navigation.pop() },
                    componentContext
                )

                RootComponent.Child.Attendance(component)
            }
            Config.Profile -> {
                val component = DefaultProfileComponent(componentContext)
                RootComponent.Child.Profile(component)
            }
            Config.Classbook -> {
                val component = pagesFactoryComponent.create(
                    onGroupClicked = {
                        navigation.push(Config.Attendance(it))
                    },
                    componentContext)
                RootComponent.Child.Pages(component)
            }

        }

    }

    sealed interface Config: Parcelable {

        @Parcelize
        data object Profile: Config
        @Parcelize
        data object Classbook: Config

        @Parcelize
        data class Attendance(val studentGroup: StudentGroup): Config

    }

    @AssistedFactory
    interface Factory{

        fun create(
            @Assisted componentContext: ComponentContext
        ) : DefaultRootComponent
    }
}