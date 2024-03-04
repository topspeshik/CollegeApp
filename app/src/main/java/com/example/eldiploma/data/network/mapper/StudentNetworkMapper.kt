package com.example.eldiploma.data.network.mapper

import com.example.eldiploma.data.network.dto.StudentDto
import com.example.eldiploma.domain.entity.Student


fun Student.toDto() : StudentDto = StudentDto(id, name, lastname, phoneNumber)

fun StudentDto.toEntity() : Student = Student(id, name, lastname, phoneNumber)

fun List<StudentDto>.toEntities() : List<Student> = map{it.toEntity()}
