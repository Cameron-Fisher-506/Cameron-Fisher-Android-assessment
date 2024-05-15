package com.glucode.about_you

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.glucode.about_you.engineers.EngineersFlowManager
import com.glucode.about_you.engineers.EngineersSharedViewModel
import com.glucode.about_you.model.room.EngineersDatabase
import com.glucode.about_you.model.room.IEngineersDao
import com.glucode.about_you.model.room.upsert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

class MainActivity : BaseActivity() {
    private val engineersSharedViewModel by viewModels<EngineersSharedViewModel>(factoryProducer = { getViewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var viewModelFactory: ViewModelFactory<EngineersSharedViewModel>
        val navController = findNavController(R.id.fragment_host)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_host).navigateUp() || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //TODO: registerForActivityResult was not working for some odd reason
        super.onActivityResult(requestCode, resultCode, data)
        engineersSharedViewModel.drawableResource.value = data?.data ?: Uri.EMPTY
        EngineersFlowManager.selectedEngineer.defaultImageName = engineersSharedViewModel.drawableResource.value.toString()
        val engineerDao: IEngineersDao = EngineersDatabase.getDatabase(application).engineerDao()
        runBlocking {
            engineerDao.upsert(EngineersFlowManager.selectedEngineer, engineerDao)
        }
    }
}