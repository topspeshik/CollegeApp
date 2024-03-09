package com.example.eldiploma.presentation.searchStudents

import com.example.eldiploma.domain.entity.Student
import kotlinx.coroutines.flow.StateFlow

interface SearchStudentsComponent {

    val model: StateFlow<SearchStudentsStore.State>

    fun changeSearchQuery(query: String)

    fun onClickBack()

    fun onClickSearch()

    fun onStudentClick(student: Student)
}