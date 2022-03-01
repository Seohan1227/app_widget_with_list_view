package com.example.home_widget

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews


class WidgetProvider : AppWidgetProvider() {

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?) {
        appWidgetIds?.forEach { appWidgetId ->
            val serviceIntent = Intent(context, MyRemoteViewsService::class.java)
            val remoteViews = RemoteViews(context?.packageName, R.layout.app_widget)
            remoteViews.setRemoteAdapter(R.id.listViewAppWidget, serviceIntent)
            remoteViews.setPendingIntentTemplate(R.id.listViewAppWidget,goActivity(context))

            appWidgetManager?.updateAppWidget(appWidgetId, remoteViews)
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun goActivity(context: Context?): PendingIntent {
        val intent = Intent(context,MainActivity::class.java)
        return PendingIntent.getActivity(context, 0, intent, flags)
    }

    companion object {
        const val flags = PendingIntent.FLAG_UPDATE_CURRENT
    }
}
