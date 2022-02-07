package com.kristina.feedthebeast.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kristina.feedthebeast.database.FeedTheBeastDatabase
import com.kristina.feedthebeast.database.FeedTheBeastDatabaseDao
import com.kristina.feedthebeast.database.feedingData.Feeding
import com.kristina.feedthebeast.database.users.User
import kotlinx.coroutines.*
import android.appwidget.AppWidgetManager

import android.content.ComponentName
import android.content.Context

import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.kristina.feedthebeast.FeedTheBeastWidget


class BeastViewModel(
    val database: FeedTheBeastDatabaseDao,
    application: Application

) : AndroidViewModel(application) {


    private val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var user: User? = null

    val userId = 1L

    var _feedData = database.getUserFeedings(userId)

    companion object {
        private const val SCORE_DIVIDER = 15
    }

    private val _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
        get() = _score

    private val _animate = MutableLiveData<Boolean>(false)
    val animate: LiveData<Boolean>
        get() = _animate

    fun feed() {
        uiScope.launch {

            _score.value = _score.value?.plus(1)

            setFeedingToDatabase()
            getFeedingData()

            if (checkScore()) {
                _animate.value = true
            }

                  updateWorkoutsWidget(getApplication<Application>().applicationContext)

        }
    }

    private fun updateWorkoutsWidget(context: Context) {
        val intent = Intent(context, FeedTheBeastWidget::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE

        val ids = AppWidgetManager.getInstance(context).getAppWidgetIds(
            ComponentName(
                context,
                FeedTheBeastWidget::class.java
            )
        )

        if (ids != null && ids.isNotEmpty()) {
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
            context.sendBroadcast(intent);
        }
    }

    private suspend fun getScore(): User? {
        return withContext(Dispatchers.IO) {
            database.getUserByName("kristina")
        }
    }

    private suspend fun setFeedingToDatabase() {
        val feeding = Feeding()

        feeding.userId = userId
        feeding.score = _score.value!!

        // we get sleepNight - create another coroutine in the IO context using the IO dispatcher
        return withContext(Dispatchers.IO) {
            database.insertFeeding(feeding)
        }
    }

    private suspend fun getFeedingData() {
        withContext(Dispatchers.IO) {
            //feedData = database.getUserFeedings(userId)
        }
    }

    private fun checkScore(): Boolean {
        return _score.value?.rem(SCORE_DIVIDER) == 0
    }

    fun doneAnimation() {
        _animate.value = false
    }

}