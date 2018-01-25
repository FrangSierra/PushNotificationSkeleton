package com.example.frangsierra.pushnotificationskeleton.notifications

import android.app.Notification
import android.content.Context
import android.support.v4.app.NotificationCompat

/** Builder interface for build the [Notification] from a [PushNotificationItem]. */
interface NotificationBuilder {
    fun build(context: Context, item: PushNotificationItem): Notification
}

class DefaultNotificationBuilder : NotificationBuilder {

    override fun build(context: Context, item: PushNotificationItem): Notification {
        val builder = NotificationCompat.Builder(context, item.channel().channelId)
            .setSmallIcon(item.smallIcon())
            .setContentTitle(item.title())
            .setContentText(item.message())
            .setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentIntent(item.pendingIntent())
        return builder.build()
    }
}