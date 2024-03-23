package com.example.eldiploma.data.network.mapper

import com.example.eldiploma.data.network.dto.GroupDto
import com.example.eldiploma.data.network.dto.TeacherDto
import com.example.eldiploma.domain.entity.Group
import com.example.eldiploma.domain.entity.Teacher

fun Teacher.toDto() : TeacherDto = TeacherDto(id,
    firstName,
    lastName,
    phoneNumber, emailAddress )

fun TeacherDto.toEntity() : Teacher =  Teacher(id,
    firstName,
    lastName,
    phoneNumber, emailAddress)

fun List<TeacherDto>.toEntities() : List<Teacher> = map{it.toEntity()}
