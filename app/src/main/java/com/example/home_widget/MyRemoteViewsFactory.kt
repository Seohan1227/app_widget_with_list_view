package com.example.home_widget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService

class MyRemoteViewsFactory(private val context: Context): RemoteViewsService.RemoteViewsFactory{
    private val itemList = mutableListOf<Int>()

    private fun updateViews() {
        itemList.clear()
        for (i in 0..23) {
            itemList.add(i)
        }
    }

    // 최초 생성(구성) 시, 호출
    override fun onCreate() {
        updateViews()
    }
    // 원격 어댑터에서 항목 추가 및 제거 등 데이터 변경이 발생했을 때 호출
    override fun onDataSetChanged() {
        updateViews()
    }

    // 해당 팩토리와 연결된 마지막 RemoteViewsAdapter 가 바인딩 해제될 때 호출
    override fun onDestroy() {

    }

    // 리스트 뷰의 항목 개수
    override fun getCount(): Int {
        return itemList.size
    }

    // 각 항목을 구현하기 위해 호출, 매개변수 값을 참조하여 각 항목을 구성하기위한 로직
    override fun getViewAt(position: Int): RemoteViews {
        val remoteViews =  RemoteViews(context.packageName,R.layout.app_widget_item)

        val title = "position: ${itemList[position]}"
        remoteViews.setTextViewText(R.id.textView8,title)

        // fillInIntent 의 설정
        val fillInIntent = Intent()
        fillInIntent.putExtra(EXTRA_FROM_APP_WIDGET,position.toString())
        remoteViews.setOnClickFillInIntent(R.id.linearLayoutAppWidgetItem,fillInIntent)

        return remoteViews
    }

    // 로딩 뷰를 표현하기 위해 호출
    override fun getLoadingView(): RemoteViews? {
        return null
    }

    // 팩토리에서 반환될 뷰 유형의 수
    override fun getViewTypeCount(): Int {
        return 1
    }

    // 각 항목의 식별자 값을 얻기 위해 호출
    override fun getItemId(position: Int): Long {
        return 0
    }

    // 동일한 ID가 항상 동일한 개체를 참조하는 경우 true
    override fun hasStableIds(): Boolean {
        return true
    }

    companion object {
        const val EXTRA_FROM_APP_WIDGET = "extraFromAppWidget"
    }
}