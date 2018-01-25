package com.example.frangsierra.pushnotificationskeleton.model

import com.example.frangsierra.pushnotificationskeleton.notifications.NotificationType

data class PushNotification(val id: String,
                            val notificationType: NotificationType,
                            val title: String,
                            val message: String,
                            val someStringData: String)