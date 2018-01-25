package com.example.frangsierra.pushnotificationskeleton.dagger

import com.example.frangsierra.pushnotificationskeleton.MainActivity
import com.example.frangsierra.pushnotificationskeleton.core.App
import com.example.frangsierra.pushnotificationskeleton.notifications.PushNotificationManager
import com.example.frangsierra.pushnotificationskeleton.service.MessagingController
import com.google.firebase.iid.FirebaseInstanceIdService
import dagger.Component

@AppScope
@Component(modules = arrayOf(AppModule::class, PushNotificationModule::class))
interface AppComponent {

    fun inject(application: App)

    fun inject(firebaseInstanceIdService: FirebaseInstanceIdService)

    fun inject(messagingController: MessagingController)

    fun inject(pushNotificationMAnager: PushNotificationManager)

    fun inject(mainActivity: MainActivity)
}