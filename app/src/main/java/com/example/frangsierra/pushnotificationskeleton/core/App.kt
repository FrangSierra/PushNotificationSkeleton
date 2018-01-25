package com.example.frangsierra.pushnotificationskeleton.core

import android.app.Application
import com.example.frangsierra.pushnotificationskeleton.BuildConfig
import com.example.frangsierra.pushnotificationskeleton.dagger.AppComponent
import com.example.frangsierra.pushnotificationskeleton.dagger.AppModule
import com.example.frangsierra.pushnotificationskeleton.dagger.DaggerAppComponent
import com.example.frangsierra.pushnotificationskeleton.utils.DebugTree
import com.example.frangsierra.pushnotificationskeleton.utils.Grove
import org.jetbrains.annotations.TestOnly
import kotlin.properties.Delegates

private var _app: App? = null

private var _prefs: Prefs by Delegates.notNull()
private var _appComponent: AppComponent? = null
val app: App get() = _app!!
val prefs: Prefs get() = _prefs
val appComponent: AppComponent get() = _appComponent!!

@TestOnly
fun setAppComponent(component: AppComponent) {
    _appComponent = component
}

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        _app = this
        _prefs = Prefs(this)
        if (BuildConfig.DEBUG) Grove.plant(DebugTree(true))

        if (_appComponent == null) {
            _appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
        }

    }
}