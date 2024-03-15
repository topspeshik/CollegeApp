package com.example.eldiploma.presentation.searchStudents

import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.entity.StudentGroup
import kotlinx.coroutines.flow.StateFlow

interface SearchStudentsComponent {

    val model: StateFlow<SearchStudentsStore.State>

    fun changeSearchQuery(query: String)

    fun onClickBack()

    fun onClickSearch()

    fun onStudentClick(studentGroup: StudentGroup)

    fun onGroupClick(studentGroup: StudentGroup)

}