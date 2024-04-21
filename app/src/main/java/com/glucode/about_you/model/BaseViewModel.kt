package com.glucode.about_you.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var ioDispatcher: CoroutineDispatcher = Dispatchers.IO
}