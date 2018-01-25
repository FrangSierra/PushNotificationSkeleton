package com.example.frangsierra.pushnotificationskeleton.core

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class Prefs(context: Context) {
    val PREFS_FILENAME = "notifications.prefs"
    val NOTIFICATION_INFO = "notification_info"
    val NOTIFICATIONS_ENABLED = "notifications_enabled"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    val gson: Gson by lazy { GsonBuilder().create() }

    var notificationMap: Map<String, String>
        get() = gson.fromJson(prefs.getString(NOTIFICATION_INFO, gson.toJson(HashMap<String, String>())),
            object : TypeToken<HashMap<String, String>>() {}.type)
        set(value) {
            prefs.edit().putString(NOTIFICATION_INFO, gson.toJson(value)).apply()
        }

    var notEnabled: Boolean
        get() = prefs.getBoolean(NOTIFICATIONS_ENABLED, true)
        set(value) = prefs.edit().putBoolean(NOTIFICATIONS_ENABLED, value).apply()

    fun clearNotificationInfo() {
        notificationMap = HashMap()
    }
}
