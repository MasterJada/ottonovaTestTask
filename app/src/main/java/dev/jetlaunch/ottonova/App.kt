package dev.jetlaunch.ottonova

import android.app.Application
import dev.jetlaunch.ottonova.di.AppModule
import dev.jetlaunch.ottonova.di.DaggerAppComponent
import dev.jetlaunch.ottonova.di.Injector

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.component = DaggerAppComponent.builder().appModule(AppModule()).build()
    }
}