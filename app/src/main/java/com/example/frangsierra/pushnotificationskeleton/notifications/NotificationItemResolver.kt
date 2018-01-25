package com.example.frangsierra.pushnotificationskeleton.notifications

import android.content.Context
import com.example.frangsierra.pushnotificationskeleton.R
import com.example.frangsierra.pushnotificationskeleton.core.prefs
import com.example.frangsierra.pushnotificationskeleton.model.*
import com.example.frangsierra.pushnotificationskeleton.notifications.NotificationType.*
import com.example.frangsierra.pushnotificationskeleton.service.MessagingController

/** Class in charge to returns a [PushNotificationItem] based on the information of the data received. */
interface NotificationItemResolver {
    /** Returns a [PushNotificationItem] depending of the data from the map. */
    fun resolve(context: Context, data: Map<String, String>): PushNotificationItem
}

class PushNotificationItemResolver : NotificationItemResolver {

    override fun resolve(context: Context, data: Map<String, String>): PushNotificationItem {
        val pushMessage = castData(data)
        val notificationMap = prefs.notificationMap.mapValues { valueOf(it.value) }

        prefs.notificationMap = //Update the current notification map adding +1 to the current type of notification
            notificationMap.plus(pushMessage.id to pushMessage.notificationType).mapValues { it.value.name }

        return when (pushMessage.notificationType) {
            TYPE_1 -> Type1Notification(context = context,
                title = pushMessage.title,
                notificationsCount = notificationMap.values.filter { it == TYPE_1 }.count(),
                iconId = R.drawable.notification_template_icon_bg)
            TYPE_2 -> Type2Notification(context = context,
                title = pushMessage.title,
                someStringData = pushMessage.someStringData,
                notificationsCount = notificationMap.values.filter { it == TYPE_1 }.count(),
                iconId = R.drawable.notification_template_icon_bg)
            TYPE_3 -> Type3Notification(context = context,
                title = pushMessage.title,
                someIntData = 40, // Retrive your custom data from the map
                notificationsCount = notificationMap.values.filter { it == TYPE_1 }.count(),
                iconId = R.drawable.notification_template_icon_bg)
            CUSTOM -> CustomNotification(context = context,
                title = pushMessage.title,
                message = pushMessage.message,
                iconId = R.drawable.notification_template_icon_bg)
        }
    }

    /** Cast the map of data from the push message to [PushNotification] data class. */
    private fun castData(data: Map<String, String>) = PushNotification(
        id = data[MessagingController.REMOTE_MESSAGE_ID] ?: "0",
        notificationType = NotificationType.valueOf(data[MessagingController.TYPE]!!.toUpperCase()),
        someStringData = data[MessagingController.SOME_DATA] ?: "",
        message = data[MessagingController.NOTI_MESSAGE] ?: "",
        title = data[MessagingController.NOTI_TITLE] ?: "")
}