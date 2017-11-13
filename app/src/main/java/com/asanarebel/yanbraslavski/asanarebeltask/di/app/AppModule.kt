package com.affinitas.task.di.app

import com.affinitas.task.api.ApiService
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainContract
import com.asanarebel.yanbraslavski.asanarebeltask.main.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule {

    @Provides
    @Singleton
    open fun provideApi() = ApiService.create()

    @Provides
    open fun provideMainPresenter(apiService: ApiService): MainContract.MainPresenter {
        return MainPresenter(apiService)
    }
}