package com.glucode.about_you.model.repository

import android.app.Application
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.model.room.EngineersDatabase
import com.glucode.about_you.model.room.IEngineersDao
import com.glucode.about_you.model.room.upsert
import com.glucode.about_you.utils.DataAccessStrategyUtils
import com.glucode.about_you.utils.Resource

class EngineersRepository(application: Application) {
    private val engineerDao: IEngineersDao = EngineersDatabase.getDatabase(application).engineerDao()

    suspend fun fetchEngineers(): Resource<List<Engineer>> {
        return DataAccessStrategyUtils.lazyCache(
            { EngineersDatabase.getResource { engineerDao.getAll() } },
            { EngineersDatabase.getResource { MockData.engineers } },
            { engineerDao.upsert(it, engineerDao) }
        )
    }

    suspend fun upsert(engineer: Engineer) = engineerDao.upsert(engineer, engineerDao)
}