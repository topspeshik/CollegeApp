package com.example.eldiploma.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eldiploma.data.local.dao.GroupDao
import com.example.eldiploma.data.local.dao.StudentDao
import com.example.eldiploma.data.local.dao.StudentGroupDao
import com.example.eldiploma.data.local.model.AttendanceDbModel
import com.example.eldiploma.data.local.model.GroupDbModel
import com.example.eldiploma.data.local.model.MeetingDbModel
import com.example.eldiploma.data.local.model.StudentDbModel
import com.example.eldiploma.data.local.model.StudentGroupDbModel

@Database(
    entities = [
        AttendanceDbModel::class,
        GroupDbModel::class,
        MeetingDbModel::class,
        StudentDbModel::class,
        StudentGroupDbModel::class
               ],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase(){


    abstract fun studentDao(): StudentDao
    abstract fun studentGroupDao(): StudentGroupDao
    abstract fun groupDao(): GroupDao

    companion object {

        private var INSTANCE: LocalDatabase? = null
        private var LOCK = Any()
        private var DB_NAME = "LocalDatabase"


        fun getInstance(context: Context) : LocalDatabase{
            INSTANCE?.let { return it }

            synchronized(LOCK){
                INSTANCE?.let { return it }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = LocalDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = database
                return database
            }
        }
    }
}