package com.example.eldiploma.presentation.pagesClassbook.search

import com.example.eldiploma.domain.entity.StudentGroup
import kotlinx.coroutines.flow.StateFlow

interface SearchComponent {

    val model: StateFlow<SearchStore.State>

    fun changeSearchQuery(query: String)

    fun onClickBack()

    fun onClickSearch()

    fun onStudentClick(studentGroup: StudentGroup)

    fun onGroupClick(studentGroup: StudentGroup)

}