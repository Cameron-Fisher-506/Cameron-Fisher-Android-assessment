package com.glucode.about_you

import android.app.Application
import com.glucode.about_you.di.ApplicationComponent
import com.glucode.about_you.di.ApplicationModule
import com.glucode.about_you.di.DaggerApplicationComponent

class AboutYouApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}