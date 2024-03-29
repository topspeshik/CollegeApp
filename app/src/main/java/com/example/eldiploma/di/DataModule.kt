package com.example.eldiploma.di

import android.content.Context
import com.example.eldiploma.data.local.LocalDatabase
import com.example.eldiploma.data.local.dao.AttendanceDao
import com.example.eldiploma.data.local.dao.GroupDao
import com.example.eldiploma.data.local.dao.MeetingDao
import com.example.eldiploma.data.local.dao.StudentDao
import com.example.eldiploma.data.local.dao.StudentGroupDao
import com.example.eldiploma.data.local.dao.TeacherDao
import com.example.eldiploma.data.local.repository.AttendanceRepositoryImpl
import com.example.eldiploma.data.local.repository.GroupRepositoryImpl
import com.example.eldiploma.data.local.repository.MeetingRepositoryImpl
import com.example.eldiploma.data.local.repository.StudentGroupRepositoryImpl
import com.example.eldiploma.data.local.repository.StudentRepositoryImpl
import com.example.eldiploma.data.local.repository.TeacherRepositoryImpl
import com.example.eldiploma.data.network.ApiFactory
import com.example.eldiploma.data.network.ApiService
import com.example.eldiploma.data.network.repository.AttendanceNetworkRepositoryImpl
import com.example.eldiploma.data.network.repository.StudentNetworkRepositoryImpl
import com.example.eldiploma.data.network.repository.TeacherNetworkRepositoryImpl
import com.example.eldiploma.domain.local.repository.AttendanceRepository
import com.example.eldiploma.domain.local.repository.GroupRepository
import com.example.eldiploma.domain.local.repository.MeetingRepository
import com.example.eldiploma.domain.local.repository.StudentGroupRepository
import com.example.eldiploma.domain.local.repository.StudentRepository
import com.example.eldiploma.domain.local.repository.TeacherRepository
import com.example.eldiploma.domain.network.repository.AttendanceNetworkRepository
import com.example.eldiploma.domain.network.repository.StudentNetworkRepository
import com.example.eldiploma.domain.network.repository.TeacherNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindStudentRepository(impl: StudentRepositoryImpl) : StudentRepository

    @ApplicationScope
    @Binds
    fun bindStudentNetworkRepository(impl: StudentNetworkRepositoryImpl) : StudentNetworkRepository

    @ApplicationScope
    @Binds
    fun bindStudentGroupRepository(impl: StudentGroupRepositoryImpl) : StudentGroupRepository

    @ApplicationScope
    @Binds
    fun bindAttendanceRepository(impl: AttendanceRepositoryImpl) : AttendanceRepository

    @ApplicationScope
    @Binds
    fun bindMeetingRepository(impl: MeetingRepositoryImpl) : MeetingRepository

    @ApplicationScope
    @Binds
    fun bindAttendanceNetworkRepository(impl: AttendanceNetworkRepositoryImpl) : AttendanceNetworkRepository

    @ApplicationScope
    @Binds
    fun bindTeacherNetworkRepository(impl: TeacherNetworkRepositoryImpl) : TeacherNetworkRepository

    @ApplicationScope
    @Binds
    fun bindTeacherRepository(impl: TeacherRepositoryImpl) : TeacherRepository

    @ApplicationScope
    @Binds
    fun bindGroupRepository(impl: GroupRepositoryImpl) : GroupRepository

    companion object{

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService

        @ApplicationScope
        @Provides
        fun provideDatabase(context: Context): LocalDatabase {
            return LocalDatabase.getInstance(context)
        }

        @ApplicationScope
        @Provides
        fun provideStudentDao(database: LocalDatabase): StudentDao{
            return database.studentDao()
        }

        @ApplicationScope
        @Provides
        fun provideStudentGroupDao(database: LocalDatabase): StudentGroupDao{
            return database.studentGroupDao()
        }

        @ApplicationScope
        @Provides
        fun provideAttendanceDao(database: LocalDatabase): AttendanceDao{
            return database.attendanceDao()
        }

        @ApplicationScope
        @Provides
        fun provideMeetingDao(database: LocalDatabase): MeetingDao{
            return database.meetingDao()
        }

        @ApplicationScope
        @Provides
        fun provideTeacherDao(database: LocalDatabase): TeacherDao{
            return database.teacherDao()
        }

        @ApplicationScope
        @Provides
        fun provideGroupDao(database: LocalDatabase): GroupDao{
            return database.groupDao()
        }
    }
}