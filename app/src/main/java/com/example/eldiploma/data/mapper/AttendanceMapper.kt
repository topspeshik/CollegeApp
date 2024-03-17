package com.example.eldiploma.data.mapper

import com.example.eldiploma.data.local.model.AttendanceDbModel
import com.example.eldiploma.domain.entity.Attendance

fun Attendance.toDbModel() : AttendanceDbModel = AttendanceDbModel(
    id,
    studentId,
    isPresent,
    meetingId,
    meetingName,
    studentName
)

fun AttendanceDbModel.toEntity() : Attendance =  Attendance(
    id,
    studentId,
    isPresent,
    meetingId,
    meetingName,
    studentName)

fun List<AttendanceDbModel>.toEntities() : List<Attendance> = map{it.toEntity()}
