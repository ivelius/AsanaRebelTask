package com.affinitas.task.main.di.app

import com.affinitas.task.di.app.AppComponent
import com.affinitas.task.di.app.TestAppModule
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ivelius on 10/28/2017.
 */
@Singleton
@Component(modules = arrayOf(TestAppModule::class))
interface TestAppComponent : AppComponent {
    override  fun inject(mainActivity: MainActivity)
}