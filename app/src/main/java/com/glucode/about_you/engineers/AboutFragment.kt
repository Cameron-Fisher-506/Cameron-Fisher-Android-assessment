package com.glucode.about_you.engineers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composelibrary.informational.ProfileCardView
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.models.QuickStats
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.views.QuestionCardView


class AboutFragment: Fragment() {
    private lateinit var binding: FragmentAboutBinding

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
        val statsMap: Map<String, Int> = mapOf(
            "Years" to quickStats.years,
            "Coffees" to quickStats.coffees,
            "Bugs" to quickStats.bugs
        )
        binding.composeView.setContent {
            ProfileCardView(R.drawable.ic_person, name, role, statsMap) {
                Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                    startActivityForResult(this, 0)
                }
            }
        }
    }

    private fun setUpQuestions() {
        val engineerName = arguments?.getString("name")
        val engineer = MockData.engineers.firstOrNull { it.name == engineerName }

        if (engineer != null) {
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