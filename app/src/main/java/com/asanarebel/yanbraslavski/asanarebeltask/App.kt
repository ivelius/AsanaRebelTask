package com.asanarebel.yanbraslavski.asanarebeltask

import android.app.Application
import com.affinitas.task.di.app.AppComponent
import com.affinitas.task.di.app.AppModule
import com.affinitas.task.di.app.DaggerAppComponent

/**
 * Created by yan.braslavski on 11/13/17.
 */
class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule())
                .build()
    }
}
