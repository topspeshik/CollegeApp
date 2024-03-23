package com.example.eldiploma.data.mapper

import com.example.eldiploma.data.local.model.TeacherDbModel
import com.example.eldiploma.domain.entity.Teacher

fun Teacher.toDbModel() : TeacherDbModel = TeacherDbModel(id,
    firstName ,
    lastName ,
    phoneNumber, emailAddress
)

fun TeacherDbModel.toEntity() : Teacher =  Teacher(id,
    firstName ,
    lastName ,
    phoneNumber, emailAddress)

fun List<TeacherDbModel>.toEntities() : List<Teacher> = map{it.toEntity()}