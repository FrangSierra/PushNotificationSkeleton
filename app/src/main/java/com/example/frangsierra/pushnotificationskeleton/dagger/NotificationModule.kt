package com.example.frangsierra.pushnotificationskeleton.dagger

import android.app.NotificationManager
import android.content.Context
import com.example.frangsierra.pushnotificationskeleton.notifications.DefaultNotificationBuilder
import com.example.frangsierra.pushnotificationskeleton.notifications.NotificationBuilder
import com.example.frangsierra.pushnotificationskeleton.notifications.NotificationItemResolver
import com.example.frangsierra.pushnotificationskeleton.notifications.PushNotificationItemResolver
import dagger.Module
import dagger.Provides

@Module
class PushNotificationModule {
    @Provides
    @AppScope
    fun providePushNotificationManager(context: Context): NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @Provides
    @AppScope
    fun providePushNotificationItemResolver(): NotificationItemResolver =
        PushNotificationItemResolver()

    @Provides
    @AppScope
    fun provideNotificationBuilder(): NotificationBuilder =
        DefaultNotificationBuilder()
}