package com.example.eldiploma.data.mapper

import com.example.eldiploma.data.local.model.StudentGroupDbModel
import com.example.eldiploma.data.network.dto.StudentGroupDto
import com.example.eldiploma.domain.entity.StudentGroup

fun StudentGroup.toDbModel() : StudentGroupDbModel = StudentGroupDbModel(id, studentId, name, groupId, groupName)

fun StudentGroupDbModel.toEntity() : StudentGroup = StudentGroup(id, studentId, name, groupId, groupName)

fun List<StudentGroupDbModel>.toEntities() : List<StudentGroup> = map{it.toEntity()}
