package com.kristina.feedthebeast

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class FeedTheBeastWidget : AppWidgetProvider() {

    private var score = 0

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        val scoreStr = "Score: $score"
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, scoreStr)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (intent.getIntExtra("SCORE_EXTRA", -1) != -1) {
                score = intent.getIntExtra("SCORE_EXTRA", -1);
            }
        }
        super.onReceive(context, intent)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    score: String
) {
    val views = RemoteViews(context.packageName, R.layout.feed_the_beast_widget)
    views.setTextViewText(R.id.score, score)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}