package com.example.eldiploma.data.mapper

import com.example.eldiploma.data.local.model.GroupDbModel
import com.example.eldiploma.data.network.dto.GroupDto
import com.example.eldiploma.domain.entity.Group

fun Group.toDbModel() : GroupDbModel = GroupDbModel(id, name, leadId, leadName)

fun GroupDbModel.toEntity() : Group =  Group(id, name, leadId, leadName)

fun List<GroupDbModel>.toEntities() : List<Group> = map{it.toEntity()}
