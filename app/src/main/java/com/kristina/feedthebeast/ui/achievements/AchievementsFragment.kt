package com.kristina.feedthebeast.ui.achievements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kristina.feedthebeast.database.FeedTheBeastDatabase
import com.kristina.feedthebeast.database.users.User
import com.kristina.feedthebeast.databinding.FragmentAchievementsBinding

class AchievementsFragment : Fragment() {

    lateinit var binding : FragmentAchievementsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAchievementsBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = FeedTheBeastDatabase.getInstance(application).feedTheBeastDatabaseDao

        val viewModelFactory = AchievementsViewModelFactory(dataSource, application)

        val resultsViewModel =
            ViewModelProvider(this, viewModelFactory).get(AchievementsViewModel::class.java)

        val adapter = AchievementsAdapter()

        binding.achievementsList.layoutManager = LinearLayoutManager(context)
        binding.achievementsList.adapter = adapter

        resultsViewModel.achievements.observe(viewLifecycleOwner) {
            it?.let {
                adapter.data = it
            }
        }


        return binding.root
    }

}