package com.kristina.feedthebeast

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.kristina.feedthebeast.database.FeedTheBeastDatabase
import com.kristina.feedthebeast.databinding.FragmentStartBinding
import com.kristina.feedthebeast.ui.start.UsersAdapter
import com.kristina.feedthebeast.ui.start.UsersViewModel
import com.kristina.feedthebeast.ui.start.UsersViewModelFactory

const val BUNDLE_KEY = "USER_NAME"

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    private lateinit var userName: String
    private val googleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

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
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)


        with(binding) {

            binding.newUser.setOnClickListener{
                findNavController(view).navigate(
                    R.id.action_startFragment_to_newUserFragment
                )
            }

            signInButton.setOnClickListener {
                val signInIntent: Intent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)
        googleSignInClient.signOut()
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val currentView = view
            if (account != null && currentView != null) {
                Snackbar.make(currentView, "Welcome ${account.displayName}", 500).show()
            }
            //todo
        } catch (e: ApiException) {
        }
    }

    companion object {
        private const val RC_SIGN_IN = 123
    }

}