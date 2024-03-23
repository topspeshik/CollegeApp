package com.example.eldiploma.domain.local.repository

import com.example.eldiploma.domain.entity.Group
import com.example.eldiploma.domain.entity.Teacher

interface TeacherRepository {
    suspend fun getTeacher(id: String): Teacher

    suspend fun addTeachers(teachers: List<Teacher>)

}