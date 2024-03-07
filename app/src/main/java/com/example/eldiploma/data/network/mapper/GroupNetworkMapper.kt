package com.example.eldiploma.data.network.mapper

import com.example.eldiploma.data.network.dto.GroupDto
import com.example.eldiploma.data.network.dto.StudentDto
import com.example.eldiploma.domain.entity.Group
import com.example.eldiploma.domain.entity.Student

fun Group.toDto() : GroupDto = GroupDto(id, name, leadId, leadName)

fun GroupDto.toEntity() : Group =  Group(id, name, leadId, leadName)

fun List<GroupDto>.toEntities() : List<Group> = map{it.toEntity()}
