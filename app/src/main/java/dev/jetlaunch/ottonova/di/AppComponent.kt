package dev.jetlaunch.ottonova.di

import dagger.Component
import dev.jetlaunch.ottonova.ui.MainActivity
import dev.jetlaunch.ottonova.viewmodels.MainViewModel
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(vm: MainViewModel)
}