package com.affinitas.task.di.app

import dagger.Module

@Module
class TestAppModule : AppModule() {

    //TODO : In case we need to provide api that returns constant mocked objects
    //TODO : we can uncomment this method
//    @Provides
//    @Singleton
//    override fun provideApi(): GitHubService = ApiMock()
}