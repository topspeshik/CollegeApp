package com.example.eldiploma.data.network.mapper

import com.example.eldiploma.data.network.dto.MeetingDto
import com.example.eldiploma.domain.entity.Meeting


fun Meeting.toDto() : MeetingDto = MeetingDto(
    id,
    name,
    dateStart,
    dateEnd,
    duration,
    classroom,
    groupId,
    groupName
)


fun MeetingDto.toEntity() : Meeting =  Meeting(id,
    name,
    dateStart,
    dateEnd,
    duration,
    classroom,
    groupId,
    groupName)

fun List<MeetingDto>.toEntities() : List<Meeting> = map{it.toEntity()}
