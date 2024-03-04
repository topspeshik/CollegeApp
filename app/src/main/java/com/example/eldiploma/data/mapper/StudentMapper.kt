package com.example.eldiploma.data.mapper

import com.example.eldiploma.data.local.model.StudentDbModel
import com.example.eldiploma.domain.entity.Student

fun Student.toDbModel() : StudentDbModel = StudentDbModel(id, name, lastname, phoneNumber)

fun StudentDbModel.toEntity() : Student= Student(id, name, lastname, phoneNumber)

fun List<StudentDbModel>.toEntities() : List<Student> = map{it.toEntity()}
