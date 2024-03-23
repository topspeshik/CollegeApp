package com.example.eldiploma.data.local.repository

import com.example.eldiploma.data.local.dao.GroupDao
import com.example.eldiploma.data.local.dao.TeacherDao
import com.example.eldiploma.data.mapper.toDbModel
import com.example.eldiploma.data.mapper.toEntity
import com.example.eldiploma.domain.entity.Teacher
import com.example.eldiploma.domain.local.repository.TeacherRepository
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    private val teacherDao: TeacherDao
)  : TeacherRepository {
    override suspend fun getTeacher(id: String): Teacher {
        return teacherDao.getTeacher(id).toEntity()
    }

    override suspend fun addTeachers(teachers: List<Teacher>) {
        teacherDao.addTeacherList(teachers.map { it.toDbModel() })
    }
}