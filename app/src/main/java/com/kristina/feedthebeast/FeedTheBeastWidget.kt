package com.kristina.feedthebeast

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.provider.Settings.System.getString
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class FeedTheBeastWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
    ) {
        val views = RemoteViews(context.packageName, R.layout.feed_the_beast_widget)
        val pref = context?.getSharedPreferences("PREF_FILE_KEY", Context.MODE_PRIVATE)

        val score = pref?.getInt("SAVED_KEY", 0)
        val scoreStr = "Last Game\nScore: ${score.toString()!!}"

        views.setTextViewText(R.id.score, scoreStr)

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}