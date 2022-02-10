package com.kristina.feedthebeast.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kristina.feedthebeast.database.FeedTheBeastDatabase
import com.kristina.feedthebeast.database.users.User
import com.kristina.feedthebeast.databinding.FragmentNewUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewUserFragment : Fragment() {

    lateinit var binding: FragmentNewUserBinding
    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //todo
        val userName = "kristina"//binding.editUserName.text.toString()
        binding.addUser.setOnClickListener {
            if (userName.isNotEmpty()) {
                val application = requireNotNull(this.activity).application
                uiScope.launch(Dispatchers.IO) {
                    val dataSource =
                        FeedTheBeastDatabase.getInstance(application).feedTheBeastDatabaseDao
                    dataSource.insertUser(User(name = userName))
                }
                val listener = NewUserListener(userName)
                listener.onClick(it)
            }
        }
    }

}