package com.affinitas.task.di.app

import com.affinitas.task.api.GitHubService
import com.asanarebel.yanbraslavski.asanarebeltask.details.DetailsActivity
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailsActivity: DetailsActivity)
}