package com.example.eldiploma.data.network.mapper

import com.example.eldiploma.data.network.dto.StudentDto
import com.example.eldiploma.data.network.dto.StudentGroupDto
import com.example.eldiploma.domain.entity.Student
import com.example.eldiploma.domain.entity.StudentGroup

fun StudentGroup.toDto() : StudentGroupDto = StudentGroupDto(id, studentId, name, groupId, groupName)

fun StudentGroupDto.toEntity() : StudentGroup = StudentGroup(id, studentId, name, groupId, groupName)

fun List<StudentGroupDto>.toEntities() : List<StudentGroup> = map{it.toEntity()}
