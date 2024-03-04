package com.example.eldiploma.data.network

import com.example.eldiploma.data.network.dto.AttendanceDto
import com.example.eldiploma.data.network.dto.StudentDto
import com.example.eldiploma.data.network.dto.GenericResponseDto
import com.example.eldiploma.data.network.dto.StudentGroupDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @GET("Contact")
    suspend fun getStudents(): GenericResponseDto

    @Headers("Content-Type: application/json", "X-Skip-Duplicate-Check: true")
    @POST("Contact")
    suspend fun addStudent(@Body student: StudentDto)


    @GET("StudentGroup")
    suspend fun getStudentGroup(): GenericResponseDto

    @Headers("Content-Type: application/json", "X-Skip-Duplicate-Check: true")
    @POST("StudentGroup")
    suspend fun addStudentGroup(@Body studentGroupDto: StudentGroupDto)


    @GET("Attendance")
    suspend fun getAttendance(): GenericResponseDto

    @Headers("Content-Type: application/json", "X-Skip-Duplicate-Check: true")
    @POST("Attendance")
    suspend fun addAttendance(@Body attendanceDto: AttendanceDto)


    @GET("Group")
    suspend fun getGroups(): GenericResponseDto


    @GET("Meeting")
    suspend fun getMeeting(): GenericResponseDto
}