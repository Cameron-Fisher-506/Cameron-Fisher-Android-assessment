package com.glucode.about_you.engineers.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Engineer(
    @PrimaryKey(autoGenerate = false)
    var name: String = "",
    var role: String = "",
    var defaultImageName: String = "",
    var quickStats: QuickStats = QuickStats(0, 0, 0),
    var questions: List<Question> = emptyList()
)