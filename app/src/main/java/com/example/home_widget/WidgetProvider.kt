package com.example.home_widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.*

class WidgetProvider : AppWidgetProvider() {
    private val sdf = SimpleDateFormat("yyyy/MM/dd\nHH시 mm분 ss초 SSS", Locale.KOREA)

    // 모든 브로드캐스트에서 아래의의 각 콜백 메서드 이전에 호출
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (intent?.action == ACTION_UPDATE) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(context
                ?.let { ComponentName(it,this::class.java) })

            onUpdate(context,appWidgetManager,appWidgetIds)
        }
    }

    // 앱 위젯의 인스턴스가 처음으로 생성될 때 호출
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    // 앱 위젯의 마지막 인스턴스가 앱 위젯 호스트에서 삭제될 때 호출
    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    // 앱 위젯이 앱 위젯 호스트에서 삭제될 때마다 호출
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    // updatePeriodMillis 속성에 의해 정의된 간격으로 앱 위젯을 업데이트하기 위해 호출
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds?.forEach { appWidgetId ->
            val views: RemoteViews = setViews(context)
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }
    }

    // 위젯이 처음으로 배치될 때와 위젯의 크기가 조절될 때 호출
    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }

    private fun goActivity(context: Context?): PendingIntent {
        val intent = Intent(context,MainActivity::class.java)
        return PendingIntent.getActivity(context, 0, intent, flags)
    }

    private fun updateWidget(context: Context?): PendingIntent {
        val intent = Intent(context,WidgetProvider::class.java)
        intent.action = ACTION_UPDATE
        return PendingIntent.getBroadcast(context,0,intent,flags)
    }

    private fun setViews(context: Context?): RemoteViews {
        val remoteViews = RemoteViews(context?.packageName, R.layout.home_widget)
        remoteViews.setTextViewText(R.id.textView,sdf.format(System.currentTimeMillis()))
        remoteViews.setOnClickPendingIntent(R.id.button1,updateWidget(context))
        remoteViews.setOnClickPendingIntent(R.id.button2, goActivity(context))
        return remoteViews
    }

    companion object {
        const val ACTION_UPDATE = "actionUpdate"
        const val flags = PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
    }
}
