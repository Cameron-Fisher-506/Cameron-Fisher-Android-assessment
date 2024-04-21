package com.glucode.about_you.engineers

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EngineersSharedViewModel : ViewModel() {
    val drawableResource: MutableState<Uri> = mutableStateOf(Uri.EMPTY)
}