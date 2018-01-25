package com.example.frangsierra.pushnotificationskeleton.model

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.annotation.DrawableRes
import com.example.frangsierra.pushnotificationskeleton.MainActivity
import com.example.frangsierra.pushnotificationskeleton.notifications.PushNotificationChannel
import com.example.frangsierra.pushnotificationskeleton.notifications.PushNotificationItem

/** [PushNotificationItem] for new Custom notifications. */
class CustomNotification(private val context: Context,
                         private val title: String,
                         private val message: String,
                         @DrawableRes private val iconId: Int) : PushNotificationItem {

    override fun channel() = PushNotificationChannel.Default()

    override fun smallIcon(): Int = iconId

    override fun title(): String = title

    override fun message(): String = message

    override fun pendingIntent(): PendingIntent {
        val resultIntent = Intent(context, MainActivity::class.java)
        val requestID = System.currentTimeMillis().toInt()
        return PendingIntent.getActivity(context, requestID, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}