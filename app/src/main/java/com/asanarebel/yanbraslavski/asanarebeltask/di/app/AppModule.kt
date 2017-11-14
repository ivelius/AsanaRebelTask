package com.affinitas.task.di.app

import com.affinitas.task.api.GitHubService
import com.asanarebel.yanbraslavski.asanarebeltask.details.DetailsContract
import com.asanarebel.yanbraslavski.asanarebeltask.details.DetailsPresenterImpl
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainContract
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainPresenterImpl
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
        return MainPresenterImpl(apiService)
    }

    @Provides
    open fun provideDetailsPresenterImpl(apiService: GitHubService): DetailsContract.DetailsPresenter {
        return DetailsPresenterImpl(apiService)
    }

}