package com.kristina.feedthebeast.ui.results

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kristina.feedthebeast.database.FeedTheBeastDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ResultsViewModel(val database: FeedTheBeastDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _results = MutableLiveData<MutableList<Res>>()
    val results: MutableLiveData<MutableList<Res>>
        get() = _results

    fun add(){
        _results.value?.add(Res("krisa", 4, 123232467L))
        _results.value?.add(Res("android", 21, 345232467L))
        _results.value?.add(Res("sosat", 39, 123232467L))
    }

}