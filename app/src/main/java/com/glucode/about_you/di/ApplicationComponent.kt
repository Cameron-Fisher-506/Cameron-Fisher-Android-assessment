package com.glucode.about_you.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ManagerModule::class])
interface ApplicationComponent {
    fun getActivityComponent(presentationModule: PresentationModule): ActivityComponent
}