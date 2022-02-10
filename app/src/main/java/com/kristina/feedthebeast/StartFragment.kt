package com.kristina.feedthebeast

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import com.google.android.gms.common.AccountPicker

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kristina.feedthebeast.database.FeedTheBeastDatabase
import com.kristina.feedthebeast.database.users.User
import com.kristina.feedthebeast.databinding.FragmentStartBinding
import com.kristina.feedthebeast.ui.achievements.AchievementsAdapter
import com.kristina.feedthebeast.ui.achievements.AchievementsViewModel
import com.kristina.feedthebeast.ui.achievements.AchievementsViewModelFactory
import com.kristina.feedthebeast.ui.start.UserClickListener
import com.kristina.feedthebeast.ui.start.UsersAdapter
import com.kristina.feedthebeast.ui.start.UsersViewModel
import com.kristina.feedthebeast.ui.start.UsersViewModelFactory

const val BUNDLE_KEY = "USER_NAME"

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    private lateinit var userName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStartBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        userName = "kristina"

        val application = requireNotNull(this.activity).application

        val adapter = UsersAdapter()

        val dataSource = FeedTheBeastDatabase.getInstance(application).feedTheBeastDatabaseDao

        val viewModelFactory = UsersViewModelFactory(dataSource, application)

        val usersViewModel =
            ViewModelProvider(this, viewModelFactory).get(UsersViewModel::class.java)

        with(binding) {
            usersList.adapter = adapter
            usersList.layoutManager = LinearLayoutManager(context)
        }

        usersViewModel.users.observe(viewLifecycleOwner) {
            it?.let {
                adapter.data = it
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            binding.startFeeding.setOnClickListener {
                Navigation.findNavController(view).navigate(
                    R.id.action_startFragment_to_beastFragment, bundleOf(BUNDLE_KEY to userName)
                )
            }

            signInButton.setOnClickListener {
                val intent = AccountPicker.newChooseAccountIntent(
                    null, null, arrayOf("com.google"),
                    false, null, null, null, null
                )
                startActivityForResult(intent, 123)
            }
        }
    }

}