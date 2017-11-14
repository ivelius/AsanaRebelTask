package com.affinitas.task.di.app

import com.affinitas.task.api.GitHubService
import com.asanarebel.yanbraslavski.asanarebeltask.details.DetailsContract
import com.asanarebel.yanbraslavski.asanarebeltask.details.DetailsPresenterImpl
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainContract
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainPresenterImpl
import com.asanarebel.yanbraslavski.asanarebeltask.persistence.PresenterStateRepository
import com.asanarebel.yanbraslavski.asanarebeltask.persistence.impl.PresenterStateRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule {

    @Provides
    @Singleton
    open fun provideApi() = GitHubService.create()

    @Provides
    open fun provideMainPresenter(apiService: GitHubService, persistenceRepository: PresenterStateRepository):
            MainContract.MainPresenter {
        return MainPresenterImpl(apiService, persistenceRepository)
    }

    @Provides
    open fun provideDetailsPresenterImpl(apiService: GitHubService, persistenceRepository: PresenterStateRepository):
            DetailsContract.DetailsPresenter {
        return DetailsPresenterImpl(apiService, persistenceRepository)
    }

    @Singleton
    @Provides
    open fun providePresenterStateRepository(): PresenterStateRepository = PresenterStateRepositoryImpl()
}