package com.kristina.feedthebeast.ui.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kristina.feedthebeast.R
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

        adapter.add()
        adapter.add()
        adapter.add()
//        resultsViewModel.results.also { adapter.data = it }

        binding.resultsList.layoutManager = LinearLayoutManager(context)
        binding.resultsList.adapter = adapter


//        resultsViewModel.results.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                adapter.data = it
//            }
//        })
//        resultsViewModel.add()
        return binding.root
    }
}