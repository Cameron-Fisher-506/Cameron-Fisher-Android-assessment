package com.glucode.about_you

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.glucode.about_you.engineers.EngineersSharedViewModel


class MainActivity : AppCompatActivity() {
    private val engineersSharedViewModel by viewModels<EngineersSharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragment_host)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_host).navigateUp() || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //TODO: registerForActivityResult was not working for some odd reason
        super.onActivityResult(requestCode, resultCode, data)
        engineersSharedViewModel.drawableResource.value = data?.data ?: Uri.EMPTY
    }
}