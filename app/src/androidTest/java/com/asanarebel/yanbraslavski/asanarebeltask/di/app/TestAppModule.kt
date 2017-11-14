package com.affinitas.task.di.app

import com.affinitas.task.api.GitHubService
import com.affinitas.task.main.mocks.ApiMock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule : AppModule() {

    @Provides
    @Singleton
    override fun provideApi(): GitHubService = ApiMock()
}