package com.glucode.about_you.di

import androidx.appcompat.app.AppCompatActivity
import com.glucode.about_you.di.managers.IResourceManager
import com.glucode.about_you.di.managers.ResourceManager
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activity: AppCompatActivity) {
    @Provides
    fun resourceManager(): IResourceManager = ResourceManager(activity.resources)
}