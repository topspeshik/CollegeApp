package com.example.eldiploma.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.eldiploma.data.local.model.GroupDbModel
import com.example.eldiploma.data.local.model.StudentGroupDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Query("SELECT * FROM groups")
    fun getGroups(): List<GroupDbModel>

    @Upsert
    suspend fun addGroups(groupDbModels: List<GroupDbModel>)
}