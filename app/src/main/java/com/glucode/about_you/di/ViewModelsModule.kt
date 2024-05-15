package com.glucode.about_you.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.glucode.about_you.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelsModule {
    @Provides
    fun getViewModelFactory(providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory = ViewModelFactory(providers)
}