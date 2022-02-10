package com.kristina.feedthebeast.ui.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kristina.feedthebeast.database.FeedTheBeastDatabaseDao
import com.kristina.feedthebeast.database.achievements.Achievement

class UsersViewModel (val database: FeedTheBeastDatabaseDao, application: Application) :
    AndroidViewModel(application) {

//    private val _users = database.getAllUsers()
    val achievements: LiveData<List<Achievement>>
        get() = _achievements

}