package com.example.frangsierra.pushnotificationskeleton.notifications

import android.app.PendingIntent
import com.example.frangsierra.pushnotificationskeleton.notifications.PushNotificationChannel

/** Interface which represents a notification to be showed from a push message. */
interface PushNotificationItem {

    /** The notification channel for this push message. */
    fun channel(): PushNotificationChannel

    /** The Title of the notification. */
    fun title(): String

    /** The message of the notification. */
    fun message(): String

    /** The ID of the icon. */
    fun smallIcon(): Int

    /** The intent that will be dispatched for the current notification. */
    fun pendingIntent(): PendingIntent
}