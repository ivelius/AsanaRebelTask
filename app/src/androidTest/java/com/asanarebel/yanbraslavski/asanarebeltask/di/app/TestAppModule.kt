package com.affinitas.task.di.app

import com.affinitas.task.api.GitHubService
import com.asanarebel.yanbraslavski.asanarebeltask.details.DetailsContract
import com.asanarebel.yanbraslavski.asanarebeltask.details.DetailsPresenterImpl
import com.asanarebel.yanbraslavski.asanarebeltask.persistence.PresenterStateRepository
import dagger.Module
import dagger.Provides

@Module
class TestAppModule : AppModule() {

    //we leave a possibility to change that presenter before the injection
    var mMockedDetailsPresenter: DetailsContract.DetailsPresenter? = null

    //TODO : In case we need to provide api that returns constant mocked objects
    //TODO : we can uncomment this method
//    @Provides
//    @Singleton
//    override fun provideApi(): GitHubService = ApiMock()


    //TODO : When you need mocked presenters for your activity , this is a good place to provide them
    @Provides
    override fun provideDetailsPresenterImpl(apiService: GitHubService, persistenceRepository: PresenterStateRepository):
            DetailsContract.DetailsPresenter {
        return mMockedDetailsPresenter ?: DetailsPresenterImpl(apiService, persistenceRepository)
    }
}