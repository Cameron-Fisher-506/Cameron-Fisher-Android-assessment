package com.glucode.about_you

import com.glucode.about_you.engineers.EngineersAboutViewModel
import com.glucode.about_you.engineers.models.QuickStats
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName

class EngineersAboutViewModelTest {
    private val engineersAboutViewModel: EngineersAboutViewModel = EngineersAboutViewModel()

    @Test
    @DisplayName("Get Selected Engineer By Name")
    fun shouldReturnEngineerWhenGetSelectedEngineerByNameIsCalled() {
        val selectedEngineer = engineersAboutViewModel.getSelectedEngineerByName("Brandon")
        assertNotNull(selectedEngineer)
        assertEquals("Brandon", selectedEngineer?.name)
        assertEquals("Senior dev", selectedEngineer?.role)
    }

    @Test
    @DisplayName("Get Stats Map")
    fun shouldReturnMapWhenGetStatsMapIsCalled() {
        val statsMap = engineersAboutViewModel.getStatsMap(QuickStats(6, 5400, 1800))
        assertTrue(statsMap.isNotEmpty())
        assertEquals(6, statsMap["Years"])
        assertEquals(5400, statsMap["Coffees"])
        assertEquals(1800, statsMap["Bugs"])
    }
}