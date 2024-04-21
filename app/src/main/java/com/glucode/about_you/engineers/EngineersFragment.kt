package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.enums.Status

class EngineersFragment : EngineersBaseFragment() {
    private lateinit var binding: FragmentEngineersBinding
    private lateinit var engineersViewModel: EngineersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEngineersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        engineersViewModel = ViewModelProviders.of(this)[EngineersViewModel::class.java]
        setHasOptionsMenu(true)
        fetchEngineers()
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