package com.kristina.feedthebeast.ui.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kristina.feedthebeast.database.FeedTheBeastDatabaseDao
import com.kristina.feedthebeast.database.achievements.Achievement
import com.kristina.feedthebeast.database.users.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UsersViewModel (val database: FeedTheBeastDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _users = database.getAllUsers()
    val users: LiveData<List<User>>
        get() = _users

    fun addUser(name: String){
        val user = User()
        user.name = name

        uiScope.launch(Dispatchers.IO) {
            database.insertUser(user)
        }

    }

}