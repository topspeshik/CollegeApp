package com.example.eldiploma.presentation.pagesClassbook

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.Pages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.router.pages.select
import com.arkivanov.decompose.router.pages.selectNext
import com.arkivanov.decompose.router.pages.selectPrev
import com.arkivanov.decompose.value.Value
import com.example.eldiploma.domain.entity.StudentGroup
import com.example.eldiploma.presentation.groups.DefaultGroupsComponent
import com.example.eldiploma.presentation.classbook.DefaultClassbookComponent
import com.example.eldiploma.presentation.groupsRoot.DefaultGroupsRootComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalDecomposeApi::class)
class DefaultPagesClassbookComponent @AssistedInject constructor(
    private val classbookComponentFactory: DefaultClassbookComponent.Factory,
    private val groupsComponentFactory: DefaultGroupsRootComponent.Factory,
    @Assisted("onGroupClicked") onGroupClicked: (StudentGroup) -> Unit,
    @Assisted componentContext: ComponentContext,
) : PagesClassbookComponent, ComponentContext by componentContext {

    private val nav = PagesNavigation<Config>()

    override val pages: Value<ChildPages<*, PagesClassbookComponent.Child>> =
        childPages(
            source = nav,
            initialPages = {
                Pages(
                    items = List(2) {index -> if(index==0) Config.Groups else Config.Students},
                    selectedIndex = 0,
                )
            },
        ) { config, childComponentContext ->
           when(config){
               Config.Students -> {
                   PagesClassbookComponent.Child.Students(classbookComponentFactory.create(
                       childComponentContext))
               }
               Config.Groups -> {
                   PagesClassbookComponent.Child.Groups(groupsComponentFactory.create(
                       onGroupClicked = onGroupClicked,
                       childComponentContext))
               }
           }
        }
    sealed interface Config: Parcelable {

        @Parcelize
        data object Students: Config
        @Parcelize
        data object Groups: Config

    }
    override fun selectPage(index: Int) {
        nav.select(index = index)
    }

    override fun selectNext() {
        nav.selectNext()
    }

    override fun selectPrev() {
        nav.selectPrev()
    }

    @AssistedFactory
    interface Factory{

        fun create(
            @Assisted("onGroupClicked") onGroupClicked: (StudentGroup) -> Unit,
            @Assisted componentContext: ComponentContext
        ) : DefaultPagesClassbookComponent
    }
}
