package com.glucode.about_you.engineers

import androidx.lifecycle.ViewModel
import com.glucode.about_you.mockdata.MockData

class EngineersViewModel : ViewModel() {
    fun getSortedEngineersByYear() = MockData.engineers.sortedBy { it.quickStats.years }

    fun getSortedEngineersByBugs() = MockData.engineers.sortedBy { it.quickStats.bugs }

    fun getSortedEngineersByCoffees() = MockData.engineers.sortedBy { it.quickStats.coffees }
}