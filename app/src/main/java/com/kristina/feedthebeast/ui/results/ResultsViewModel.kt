package com.kristina.feedthebeast.ui.results

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kristina.feedthebeast.database.FeedTheBeastDatabaseDao
import com.kristina.feedthebeast.database.feedingData.Feeding

class ResultsViewModel(val database: FeedTheBeastDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private val _results = database.getAllFeedings()
    val results: LiveData<List<Feeding>>
        get() = _results

}