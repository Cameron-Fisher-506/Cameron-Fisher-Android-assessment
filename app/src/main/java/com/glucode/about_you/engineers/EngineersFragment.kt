package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.enums.Status
import com.glucode.about_you.mockdata.MockData

class EngineersFragment : EngineersBaseFragment() {
    private lateinit var binding: FragmentEngineersBinding
    private val engineersViewModel by viewModels<EngineersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEngineersBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        fetchEngineers()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_engineers, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_years) {
            setUpEngineersList(engineersViewModel.getSortedEngineersByYear())
            return true
        }
        if (item.itemId == R.id.action_bugs) {
            setUpEngineersList(engineersViewModel.getSortedEngineersByBugs())
            return true
        }
        if (item.itemId == R.id.action_coffees) {
            setUpEngineersList(engineersViewModel.getSortedEngineersByCoffees())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchEngineers() {
        engineersViewModel.fetchAllEngineers()
        engineersViewModel.engineersLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val engineerList = it.data
                    if (engineerList != null) {
                        setUpEngineersList(engineerList)
                    } else {
                        //TODO: Empty state screen
                    }
                }

                Status.ERROR -> {

                }

                else -> {}
            }
        }
    }

    private fun setUpEngineersList(engineers: List<Engineer>) {
        binding.list.adapter = EngineersRecyclerViewAdapter(engineers) {
            goToAbout(it)
        }
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }

    private fun goToAbout(engineer: Engineer) {
        val bundle = Bundle().apply {
            putString("name", engineer.name)
        }
        findNavController().navigate(R.id.action_engineersFragment_to_aboutFragment, bundle)
    }
}