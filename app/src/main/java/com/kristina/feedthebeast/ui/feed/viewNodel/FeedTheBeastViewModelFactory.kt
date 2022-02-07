package com.kristina.feedthebeast.ui.feed.viewNodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kristina.feedthebeast.database.FeedTheBeastDatabaseDao

class FeedTheBeastViewModelFactory(
    private val dataSource: FeedTheBeastDatabaseDao,
    private val application: Application

) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeastViewModel::class.java)) {
            return BeastViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}