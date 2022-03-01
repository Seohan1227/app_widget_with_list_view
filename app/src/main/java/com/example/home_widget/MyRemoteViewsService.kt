package com.example.home_widget

import android.content.Intent
import android.widget.RemoteViewsService

class MyRemoteViewsService: RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return MyRemoteViewsFactory(this.applicationContext)
    }
}