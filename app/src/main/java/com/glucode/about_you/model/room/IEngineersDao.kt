package com.glucode.about_you.model.room

import androidx.room.Dao
import androidx.room.Query
import com.glucode.about_you.engineers.models.Engineer

@Dao
interface IEngineersDao : IBaseDao<Engineer> {
    @Query("SELECT * FROM engineer ORDER BY name")
    suspend fun getAll(): List<Engineer>
}