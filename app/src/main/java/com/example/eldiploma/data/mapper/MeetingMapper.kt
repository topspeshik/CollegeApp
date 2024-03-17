package com.example.eldiploma.data.mapper

import com.example.eldiploma.data.local.model.MeetingDbModel
import com.example.eldiploma.domain.entity.Meeting

fun Meeting.toDbModel() : MeetingDbModel = MeetingDbModel(
    id ,
    name,
    dateStart,
    dateEnd,
    duration,
    classroom,
    groupId,
    groupName

)

fun MeetingDbModel.toEntity() : Meeting =  Meeting( id ,
    name,
    dateStart,
    dateEnd,
    duration,
    classroom,
    groupId,
    groupName
)

fun List<MeetingDbModel>.toEntities() : List<Meeting> = map{it.toEntity()}
