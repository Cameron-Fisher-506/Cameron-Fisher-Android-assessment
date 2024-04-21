package com.glucode.about_you.model.room

import android.content.Context
import androidx.lifecycle.liveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.utils.Resource
import kotlinx.coroutines.Dispatchers

@Database(entities = [Engineer::class], version = 1, exportSchema = false)
abstract class EngineersDatabase : RoomDatabase() {

    abstract fun engineerDao(): IEngineersDao

    companion object {
        @Volatile
        private var INSTANCE: EngineersDatabase? = null

        fun getDatabase(context: Context): EngineersDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(context.applicationContext, EngineersDatabase::class.java, "engineers").build()
                    INSTANCE = instance
                    return instance
                }
            }
        }

        inline fun <T> getLiveDataResource(crossinline daoCall: suspend () -> T?) = liveData<Resource<T>>(Dispatchers.IO) {
            emit(Resource.loading())

            val value = daoCall.invoke()
            if (value != null) {
                emit(Resource.success(value))
            } else {
                emit(Resource.error("Not cached."))
            }
        }

        suspend inline fun <T> getResource(crossinline daoCall: suspend () -> T?): Resource<T> {
            try {
                val response = daoCall.invoke()
                if (response != null) {
                    return Resource.success(response)
                }
                return Resource.error("Not found in cache.")
            } catch (e: Exception) {
                return Resource.error(e.message ?: e.toString())
            }
        }
    }
}