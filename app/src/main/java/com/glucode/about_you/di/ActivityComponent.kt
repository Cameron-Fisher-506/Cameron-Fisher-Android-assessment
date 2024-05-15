package com.glucode.about_you.di

import androidx.lifecycle.ViewModelProvider
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ViewModelsModule::class, PresentationModule::class])
interface ActivityComponent {
    fun getViewModelFactory(): ViewModelProvider.Factory
}