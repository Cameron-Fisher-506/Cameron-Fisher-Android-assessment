package com.glucode.about_you

import com.glucode.about_you.engineers.EngineersViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class EngineersViewModelTest {
    private val engineersViewModel: EngineersViewModel = EngineersViewModel()

    @Test
    @DisplayName("Get Sorted Engineers By Year")
    fun shouldReturnSortedListWhenGetSortedEngineersByYearIsCalled() {
        val sortedEngineerList = engineersViewModel.getSortedEngineersByYear()
        assertTrue(sortedEngineerList.isNotEmpty())
        assertEquals("Reenen", sortedEngineerList.first().name)
        assertEquals("Wilmar", sortedEngineerList.last().name)
    }

    @Test
    @DisplayName("Get Sorted Engineers By Bugs")
    fun shouldReturnSortedListWhenGetSortedEngineersByBugsIsCalled() {
        val sortedEngineerList = engineersViewModel.getSortedEngineersByBugs()
        assertTrue(sortedEngineerList.isNotEmpty())
        assertEquals("Eben", sortedEngineerList.first().name)
        assertEquals("Brandon", sortedEngineerList.last().name)
    }

    @Test
    @DisplayName("Get Sorted Engineers By Coffees")
    fun shouldReturnSortedListWhenGetSortedEngineersByCoffeesIsCalled() {
        val sortedEngineerList = engineersViewModel.getSortedEngineersByCoffees()
        assertTrue(sortedEngineerList.isNotEmpty())
        assertEquals("Eben", sortedEngineerList.first().name)
        assertEquals("Brandon", sortedEngineerList.last().name)
    }
}