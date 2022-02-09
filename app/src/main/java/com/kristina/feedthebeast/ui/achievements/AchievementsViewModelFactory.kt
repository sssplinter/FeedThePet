package com.kristina.feedthebeast.ui.achievements

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kristina.feedthebeast.database.FeedTheBeastDatabaseDao

class AchievementsViewModelFactory(
    private val dataSource: FeedTheBeastDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AchievementsViewModel::class.java)) {
            return AchievementsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}