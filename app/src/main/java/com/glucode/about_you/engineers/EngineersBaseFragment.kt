package com.glucode.about_you.engineers

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.glucode.about_you.MainActivity

abstract class EngineersBaseFragment : Fragment() {
    protected lateinit var mainActivity: MainActivity
    protected val engineersSharedViewModel by activityViewModels<EngineersSharedViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}