package com.example.eldiploma.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.presentation.classbook.ClassbookComponent
import com.example.eldiploma.presentation.classbook.DefaultClassbookComponent
import com.example.eldiploma.presentation.pagesClassbook.DefaultPagesClassbookComponent
import com.example.eldiploma.presentation.profile.DefaultProfileComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class DefaultRootComponent @AssistedInject constructor(
    private val pagesFactoryComponent: DefaultPagesClassbookComponent.Factory,
    @Assisted componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()
    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Classbook,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child{
        return when(config){
            Config.Profile -> {
                val component = DefaultProfileComponent(componentContext)
                RootComponent.Child.Profile(component)
            }
            Config.Classbook -> {
                val component = pagesFactoryComponent.create(componentContext)
                RootComponent.Child.Pages(component)
            }
        }

    }

    sealed interface Config: Parcelable {

        @Parcelize
        data object Profile: Config
        @Parcelize
        data object Classbook: Config

    }

    @AssistedFactory
    interface Factory{

        fun create(
            @Assisted componentContext: ComponentContext
        ) : DefaultRootComponent
    }
}