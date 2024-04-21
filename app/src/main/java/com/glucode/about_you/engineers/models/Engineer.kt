package com.glucode.about_you.engineers.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Engineer(
    @PrimaryKey(autoGenerate = false)
    var name: String = "",
    var role: String = "",
    var defaultImageName: String = "",
    @Ignore
    var quickStats: QuickStats = QuickStats(0, 0, 0),
    @Ignore
    var questions: List<Question> = emptyList()
)