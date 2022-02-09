package com.kristina.feedthebeast.ui.achievements

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kristina.feedthebeast.database.FeedTheBeastDatabaseDao
import com.kristina.feedthebeast.database.achievements.Achievement

class AchievementsViewModel(val database: FeedTheBeastDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private val _achievements = database.getAllAchievements()
    val achievements: LiveData<List<Achievement>>
        get() = _achievements

}