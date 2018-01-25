package com.example.frangsierra.pushnotificationskeleton.model

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.annotation.DrawableRes
import com.example.frangsierra.pushnotificationskeleton.MainActivity
import com.example.frangsierra.pushnotificationskeleton.notifications.PushNotificationChannel
import com.example.frangsierra.pushnotificationskeleton.notifications.PushNotificationItem

/** [PushNotificationItem] for new Type1 notifications. */
class Type1Notification(private val context: Context,
                        private val title: String,
                        private val notificationsCount: Int,
                        @DrawableRes private val iconId: Int) : PushNotificationItem {

    override fun channel() = PushNotificationChannel.Example()

    override fun smallIcon(): Int = iconId

    override fun title(): String = title

    override fun message(): String = getNotificationMessage()

    override fun pendingIntent(): PendingIntent {
        val resultIntent = Intent(context, MainActivity::class.java)
        resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val requestID = System.currentTimeMillis().toInt()
        return PendingIntent.getActivity(context, requestID, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun getNotificationMessage(): String {
        return if (notificationsCount == 0) {
            "You have a new Type1 notification"
        } else {
            "You have a $notificationsCount Type1 notifications"
        }
    }
}