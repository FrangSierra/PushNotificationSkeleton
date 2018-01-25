package com.example.frangsierra.pushnotificationskeleton.service

import com.example.frangsierra.pushnotificationskeleton.core.appComponent
import com.example.frangsierra.pushnotificationskeleton.core.prefs
import com.example.frangsierra.pushnotificationskeleton.dagger.AppScope
import com.example.frangsierra.pushnotificationskeleton.dagger.inject
import com.example.frangsierra.pushnotificationskeleton.utils.Grove
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.example.frangsierra.pushnotificationskeleton.notifications.PushNotificationManager
import javax.inject.Inject

@AppScope
class MessagingController @Inject constructor() : FirebaseMessagingService() {

    @Inject
    lateinit var pushNotification: PushNotificationManager

    companion object {
        const val INTENT_FROM_NOTIFICATION = "from_notification"
        const val REMOTE_MESSAGE_ID = "id"
        const val TYPE = "type"
        const val NOTI_TITLE = "title"
        const val NOTI_MESSAGE = "message"
        const val SOME_DATA = "some_data"
    }

    override fun onCreate() {
        inject(appComponent, this)
        super.onCreate()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        if (!prefs.notEnabled || remoteMessage == null || (remoteMessage.data.isEmpty())) {
            Grove.i { "New Message received but not managed" }
            return
        }

        Grove.d { String.format("New Message from: %s", remoteMessage.from) }
        val data = remoteMessage.data
        remoteMessage.notification?.let {
            data[NOTI_TITLE] = it.title
            data[NOTI_MESSAGE] = it.body
            //Add more data if necessary
        }
        pushNotification.show(applicationContext, data)
    }
}