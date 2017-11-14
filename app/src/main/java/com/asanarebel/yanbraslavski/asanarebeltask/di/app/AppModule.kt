package com.affinitas.task.di.app

import com.affinitas.task.api.GitHubService
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainContract
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule {

    @Provides
    @Singleton
    open fun provideApi() = GitHubService.create()

    @Provides
    open fun provideMainPresenter(apiService: GitHubService): MainContract.MainPresenter {
        return MainPresenter(apiService)
    }
}