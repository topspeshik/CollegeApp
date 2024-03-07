package com.example.eldiploma.data.local.repository

import com.example.eldiploma.data.local.dao.StudentDao
import com.example.eldiploma.data.mapper.toDbModel
import com.example.eldiploma.data.mapper.toEntities
import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.local.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentDao: StudentDao
) : StudentRepository {
    override fun getStudents(): Flow<List<Student>> = studentDao.getStudents()
        .map { it.toEntities() }

    override suspend fun addStudent(student: Student) = studentDao.addStudent(student.toDbModel())

    override suspend fun addStudents(students: List<Student>) = studentDao.addStudents(students.map{it.toDbModel()} )

    override fun getStudentByName(search: String): Flow<List<Student>> = studentDao.getStudentByName(search)
        .map{it.toEntities()}
}