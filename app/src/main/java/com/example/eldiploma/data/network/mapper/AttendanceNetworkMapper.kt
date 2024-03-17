package com.example.eldiploma.data.network.mapper

import com.example.eldiploma.data.network.dto.AttendanceDto
import com.example.eldiploma.domain.entity.Attendance

fun Attendance.toDto() : AttendanceDto = AttendanceDto(
    id,
    studentId,
    isPresent,
    meetingId,
    meetingName,
    studentName
)

fun AttendanceDto.toEntity() : Attendance =  Attendance(id,
    studentId,
    isPresent,
    meetingId,
    meetingName,
    studentName)

fun List<AttendanceDto>.toEntities() : List<Attendance> = map{it.toEntity()}
