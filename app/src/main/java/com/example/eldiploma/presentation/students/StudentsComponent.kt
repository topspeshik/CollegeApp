package com.example.eldiploma.presentation.students

import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.entity.StudentGroup
import kotlinx.coroutines.flow.StateFlow

interface StudentsComponent {

    val model: StateFlow<StudentStore.State>

    fun onClickSearch()

    fun onStudentClick(studentGroup: StudentGroup)
}