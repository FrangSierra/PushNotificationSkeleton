package com.example.frangsierra.pushnotificationskeleton.dagger

import android.app.Application
import android.content.Context
import com.example.frangsierra.pushnotificationskeleton.core.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun provideApplication(): Application = app

    @Provides
    fun provideAppContext(): Context = app
}