package com.glucode.about_you.di.managers

interface IResourceManager {
    fun getString(stringId: Int): String
    fun getString(stringId: Int, vararg args: String): String
}