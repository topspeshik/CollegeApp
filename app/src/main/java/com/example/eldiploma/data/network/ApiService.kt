package com.example.eldiploma.data.network

import com.example.eldiploma.data.network.dto.AttendanceDto
import com.example.eldiploma.data.network.dto.AttendanceResponseDto
import com.example.eldiploma.data.network.dto.StudentDto
import com.example.eldiploma.data.network.dto.GroupsResponseDto
import com.example.eldiploma.data.network.dto.MeetingResponseDto
import com.example.eldiploma.data.network.dto.StudentGroupDto
import com.example.eldiploma.data.network.dto.StudentGroupResponseDto
import com.example.eldiploma.data.network.dto.StudentsResponseDto
import com.example.eldiploma.data.network.dto.TeacherResponseDto
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("Contact")
    suspend fun getStudents(@Query("searchParams") ids: String): StudentsResponseDto

    @Headers("Content-Type: application/json", "X-Skip-Duplicate-Check: true")
    @POST("Contact")
    suspend fun addStudent(@Body student: StudentDto)


    @GET("StudentGroup")
    suspend fun getStudentGroup(@Query("searchParams") ids: String): StudentGroupResponseDto

    @Headers("Content-Type: application/json", "X-Skip-Duplicate-Check: true")
    @POST("StudentGroup")
    suspend fun addStudentGroup(@Body studentGroupDto: StudentGroupDto)


    @GET("Attendance")
    suspend fun getAttendance(): AttendanceResponseDto

    @Headers("Content-Type: application/json", "X-Skip-Duplicate-Check: true")
    @POST("Attendance")
    suspend fun addAttendance(@Body attendanceDto: AttendanceDto)

    @Headers("Content-Type: application/json")
    @PUT("Attendance/{id}")
    suspend fun updateAttendance(@Path("id") id: String, @Body present: JsonObject)


    @GET("Group")
    suspend fun getGroups(@Query("searchParams") id: String): GroupsResponseDto


    @GET("Meeting")
    suspend fun getMeeting(): MeetingResponseDto

    @GET("Lead")
    suspend fun getTeachers(): TeacherResponseDto
}