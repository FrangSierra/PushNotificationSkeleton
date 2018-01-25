package com.example.frangsierra.pushnotificationskeleton.service

import com.example.frangsierra.pushnotificationskeleton.dagger.AppScope
import com.example.frangsierra.pushnotificationskeleton.utils.Grove
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import javax.inject.Inject

@AppScope
class FirebaseInstanceService @Inject constructor() : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Grove.d { "Refreshed token: $refreshedToken" }

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        refreshedToken?.let {
            //Sent your token to your repository or server
        }
    }
}