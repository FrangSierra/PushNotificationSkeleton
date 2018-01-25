package com.example.frangsierra.pushnotificationskeleton.notifications

import android.support.annotation.StringRes
import com.example.frangsierra.pushnotificationskeleton.R

/** Possible notification channels for the application. */
sealed class PushNotificationChannel(val channelId: String, @StringRes val titleResource: Int) {
    class Example : PushNotificationChannel("example", R.string.default_notification_channel_title)
    class Default : PushNotificationChannel("default", R.string.example_notification_channel_title) //Default channel for group notifications.
}