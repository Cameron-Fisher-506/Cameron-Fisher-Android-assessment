package com.glucode.about_you.di.managers

import android.content.res.Resources

class ResourceManager(private val resources: Resources): IResourceManager {
    override fun getString(stringId: Int): String = resources.getString(stringId)
    override fun getString(stringId: Int, vararg args: String): String = resources.getString(stringId, args)
}