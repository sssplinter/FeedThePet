package com.kristina.feedthebeast.ui.results

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kristina.feedthebeast.database.FeedTheBeastDatabaseDao

class ResultsViewModelFactory(private val dataSource: FeedTheBeastDatabaseDao,
private val application: Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java)) {
            return ResultsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}