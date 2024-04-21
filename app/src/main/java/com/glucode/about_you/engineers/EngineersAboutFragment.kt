package com.glucode.about_you.engineers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.composelibrary.informational.ProfileCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.models.QuickStats
import com.glucode.about_you.views.QuestionCardView

class EngineersAboutFragment: EngineersBaseFragment() {
    private lateinit var binding: FragmentAboutBinding
    private val engineersAboutViewModel by viewModels<EngineersAboutViewModel>()

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpQuestions()
    }

    private fun setUpProfileCardView(name: String, role: String, quickStats: QuickStats) {
        binding.composeView.setContent {
            ProfileCardView(engineersSharedViewModel.drawableResource.value, name, role, engineersAboutViewModel.getStatsMap(quickStats)) {
                Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                    startActivityForResult(this, 0) //TODO: registerForActivityResult was not working for some odd reason
                }
            }
        }
    }

    private fun setUpQuestions() {
        val engineerName = arguments?.getString("name") ?: ""
        val engineer = engineersAboutViewModel.getSelectedEngineerByName(engineerName)

        if (engineer != null) {
            engineersSharedViewModel.selectedEngineer = engineer
            setUpProfileCardView(engineer.name, engineer.role, engineer.quickStats)
            engineer.questions.forEach { question ->
                val questionView = QuestionCardView(requireContext()).apply {
                    title = question.questionText
                    answers = question.answerOptions
                    selection = question.answer.index
                }
                binding.container.addView(questionView)
            }
        } else {
            //TODO: Display empty state screen
        }
    }
}