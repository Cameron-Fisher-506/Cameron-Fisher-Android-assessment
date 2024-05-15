package com.glucode.about_you

import androidx.appcompat.app.AppCompatActivity
import com.glucode.about_you.di.PresentationModule

abstract class BaseActivity : AppCompatActivity() {
    private val activityComponent by lazy {
        (application as AboutYouApplication).applicationComponent.getActivityComponent(
            PresentationModule(this)
        )
    }

    val getViewModelFactory
        get() = activityComponent.getViewModelFactory()
}