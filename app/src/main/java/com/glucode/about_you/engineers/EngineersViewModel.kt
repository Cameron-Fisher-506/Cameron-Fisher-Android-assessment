package com.glucode.about_you.engineers

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.model.BaseViewModel
import com.glucode.about_you.model.repository.EngineersRepository
import com.glucode.about_you.utils.Resource
import kotlinx.coroutines.Dispatchers

class EngineersViewModel(application: Application) : BaseViewModel(application) {
    private val engineersRepository: EngineersRepository = EngineersRepository(application)
    lateinit var engineersLiveData: LiveData<Resource<List<Engineer>>>

    fun fetchAllEngineers() {
        engineersLiveData = liveData(Dispatchers.IO) {
            emit(engineersRepository.fetchEngineers())
        }
    }
    fun getSortedEngineersByYear() = MockData.engineers.sortedBy { it.quickStats.years }

    fun getSortedEngineersByBugs() = MockData.engineers.sortedBy { it.quickStats.bugs }

    fun getSortedEngineersByCoffees() = MockData.engineers.sortedBy { it.quickStats.coffees }
}