package com.glucode.about_you.engineers

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.glucode.about_you.R
import com.glucode.about_you.engineers.models.QuickStats
import com.glucode.about_you.mockdata.MockData

class EngineersAboutViewModel : ViewModel() {
    val drawableResource: MutableState<Int> = mutableStateOf(R.drawable.ic_person)

    fun getStatsMap(quickStats: QuickStats): Map<String, Int> = mapOf(
        "Years" to quickStats.years,
        "Coffees" to quickStats.coffees,
        "Bugs" to quickStats.bugs
    )

    fun getSelectedEngineerByName(name: String) = MockData.engineers.firstOrNull { it.name == name }
}