package com.kristina.feedthebeast.ui.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kristina.feedthebeast.database.FeedTheBeastDatabase
import com.kristina.feedthebeast.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {

    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResultsBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = FeedTheBeastDatabase.getInstance(application).feedTheBeastDatabaseDao

        val viewModelFactory = ResultsViewModelFactory(dataSource, application)

        val resultsViewModel =
            ViewModelProvider(this, viewModelFactory).get(ResultsViewModel::class.java)

        val adapter = ResultAdapter()


        binding.resultsList.layoutManager = LinearLayoutManager(context)
        binding.resultsList.adapter = adapter


        resultsViewModel.results.observe(viewLifecycleOwner) {
            it?.let {
                adapter.data = it
            }
        }

        return binding.root
    }
}