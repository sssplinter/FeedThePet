package com.kristina.feedthebeast.ui.feed.viewNodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kristina.feedthebeast.database.FeedTheBeastDatabaseDao
import com.kristina.feedthebeast.database.feedingData.Feeding
import kotlinx.coroutines.*
import android.appwidget.AppWidgetManager

import android.content.ComponentName
import android.content.Context

import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.kristina.feedthebeast.database.achievements.Achievement
import com.kristina.feedthebeast.widget.FeedTheBeastWidget

const val SCORE_DIVIDER = 15
const val ACHIV_DIVIDER = 20

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

    private val _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
        get() = _score

    private val _animate = MutableLiveData<Boolean>(false)
    val animate: LiveData<Boolean>
        get() = _animate

    private val _achieveName = MutableLiveData<String>("")
    val achieveName: LiveData<String>
        get() = _achieveName

    private fun checkScore() = _score.value?.rem(SCORE_DIVIDER) == 0
    private fun checkForAchievement() = _score.value?.rem(ACHIV_DIVIDER) == 0 && _score.value != 0

    fun doneAnimation() {
        _animate.value = false
    }

    fun feed() {
        _score.value = _score.value?.plus(1)
        if (checkScore()) {
            _animate.value = true
        }
        if (checkForAchievement()) {
            //_achieve.value = true
            getAchievementName()
        }
    }

    fun setFeedingToDatabase(userName: String) {
        if (score.value != 0) {
            val feeding = Feeding()
            feeding.userName = userName
            feeding.score = _score.value!!

            uiScope.launch(Dispatchers.IO) {
                val x = database.insertFeeding(listOf(feeding))
                val y = 5
            }
        }
    }

    fun setAchievementToDatabase(userName: String) {
        val achievement = Achievement()
        achievement.userName = userName
        achievement.name = _achieveName.value!!
        achievement.score = _score.value!!

        uiScope.launch(Dispatchers.IO) {
            database.insertAchievement(achievement)
        }
    }

    fun updateWorkoutsWidget(context: Context) {
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

    private fun getAchievementName() {
        _achieveName.value = when (_score.value) {
            20 -> "Good start"
            40 -> "Happy cat"
            60 -> "Very good!"
            80 -> "So nice!!!!!"
            else -> "I'm proud of you"
        }
    }
}