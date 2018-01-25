package com.example.frangsierra.pushnotificationskeleton.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import com.example.frangsierra.pushnotificationskeleton.dagger.AppScope
import com.example.frangsierra.pushnotificationskeleton.service.MessagingController
import javax.inject.Inject

/**
 * Class in charge to manage the given data of a push message, generate the notification channel if needed
 * and show the notification.
 */
private const val DEFAULT_NOTIFICATION_ID = 0 //ID used for normal notifications
private const val CUSTOM_NOTIFICATION_ID = 1 //ID used just for custom messages and promotions

@AppScope
class PushNotificationManager @Inject constructor(private val notificationManager: NotificationManager,
                                                  private val resolver: NotificationItemResolver,
                                                  private val notificationBuilder: NotificationBuilder) {

    fun show(context: Context, data: Map<String, String>) {
        if (!data.containsKey(MessagingController.NOTI_TITLE)) return //Notifications always should have a kind type

        val kind = NotificationType.valueOf(data[MessagingController.NOTI_TITLE]!!.toUpperCase())

        val id = if (kind == NotificationType.CUSTOM) CUSTOM_NOTIFICATION_ID else DEFAULT_NOTIFICATION_ID //Normal notifications should collapse, custom ones, not
        val notification = resolver.resolve(context, data)
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            createChannel(context, notification.channel())
        }
        notificationManager.notify(id, notificationBuilder.build(context, notification))
    }

    /** Creates a notification channel for the given notification type. */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(context: Context, channel: PushNotificationChannel) {
        val channelTitle = context.getString(channel.titleResource)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val notificationChannel = NotificationChannel(channel.channelId, channelTitle, importance)
        notificationChannel.setShowBadge(true)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        notificationManager.createNotificationChannel(notificationChannel)
    }
}
