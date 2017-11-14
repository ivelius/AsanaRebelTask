package com.affinitas.task.main.mocks

import com.affinitas.task.api.GitHubService
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel
import io.reactivex.Observable

/**
 * Created by yan.braslavski on 10/28/17.
 */
class ApiMock : GitHubService {
    override fun getRepositories(username: String): Observable<List<GithubRepoResponseModel>> {
        //TODO : Fake it
        return Observable.empty()
    }
}