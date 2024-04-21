package com.glucode.about_you.engineers

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.glucode.about_you.R
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.model.BaseViewModel
import com.glucode.about_you.model.repository.EngineersRepository
import kotlinx.coroutines.Dispatchers
import java.net.URI

class EngineersSharedViewModel(
    application: Application,
    private val engineersFlowManager: EngineersFlowManager = EngineersFlowManager()
) : BaseViewModel(application) {
    private val engineersRepository: EngineersRepository = EngineersRepository(application)
    var selectedEngineer: Engineer
        get() = engineersFlowManager.selectedEngineer
        set(value) {
            engineersFlowManager.selectedEngineer = value
        }


    fun saveEngineerData(engineer: Engineer) = liveData<Unit>(Dispatchers.IO) {
        engineersRepository.upsert(engineer)
    }

    val drawableResource: MutableState<Uri> = mutableStateOf(Uri.EMPTY)

}