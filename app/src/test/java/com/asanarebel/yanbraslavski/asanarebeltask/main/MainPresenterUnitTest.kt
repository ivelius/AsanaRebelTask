package com.asanarebel.yanbraslavski.asanarebeltask.main

import com.affinitas.task.api.GitHubService
import com.affinitas.task.utils.RxUtils
import com.asanarebel.yanbraslavski.asanarebeltask.BaseUnitTest
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.Owner
import com.asanarebel.yanbraslavski.asanarebeltask.persistence.PresenterStateRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import java.io.Serializable


/**
 * Test Suit for MainPresenter
 */
class MainPresenterUnitTest : BaseUnitTest() {

    private val apiService: GitHubService = mock()
    private val persistenceRepository: PresenterStateRepository = mock()

    @Before
    fun setUp() {
        //We are not really interested in testing multithreaded loading at this point
        //So we just constrain all Rx operations for a single thread
        RxUtils.makeRxSchedulersImmediate()
    }

    @After
    fun tearDown() {
        RxUtils.resetRxSchedulers()
    }

    /**
     * Test the correctness of save state functionality.
     *
     * Steps :
     * 1 - Call save state on first presenter instance.
     * 2 - Call restore state on second presenter instance.
     * 3 - Call restore state on third instance.
     *
     * Expected :
     * The second presenter instance should have the same state as the first instance.
     * The third presenter instance should should have now  default state (reset)
     */
    @Test
    fun testSaveAndRestoreState() {

        //create fake data list
        val fakeData = ArrayList<GithubRepoResponseModel>()//emptyList<GithubRepoResponseModel>()

        //create 3 instances of the same presenter type
        val p1 = MainPresenterImpl(apiService, persistenceRepository)
        val p2 = MainPresenterImpl(apiService, persistenceRepository)
        val p3 = MainPresenterImpl(apiService, persistenceRepository)

        //set a data using reflection on first instance
        setPrivateField(p1, fakeData, "mData")

        //make sure second presenter has no data set
        assertNull(getPrivateMember(p2, "mData"))

        //call save state on first instance and then
        //restore state on second presenter
        p1.saveState()

        //mock persistenceRepository data retrieval
        Mockito.`when`(persistenceRepository.retrieve<Serializable>(any())).thenReturn(fakeData)

        //make sure repository methods are called
        verify(persistenceRepository).persist(any(), anyString())
        p2.restoreState()
        verify(persistenceRepository).retrieve<Serializable>(any())

        //make sure it has now the same data as the first one
        val p1Data = getPrivateMember(p1, "mData")
        val p2Data = getPrivateMember(p2, "mData")
        assertEquals(p1Data, p2Data)

        //mock persistenceRepository data retrieval
        Mockito.`when`(persistenceRepository.retrieve<Serializable>(any())).thenReturn(null)

        //call restore state on third presenter and make sure it is still null
        p3.restoreState()
        assertNull(getPrivateMember(p3, "mData"))
    }


    /**
     * Whenever presenter has stored data ,
     * it should be immediately shown to the view
     */
    @Test
    fun testShowCachedDataOnBind() {

        //create fake data list
        val fakeData = ArrayList<GithubRepoResponseModel>()

        //initialize presenter and put data in it
        val presenter = MainPresenterImpl(apiService, persistenceRepository)
        setPrivateField(presenter, fakeData, "mData")
        val presenterSpy = spy(presenter)
        val mainView: MainContract.MainView = mock()

        //verify show data was called on the view immediately after we bind the view
        presenterSpy.bind(mainView)
        verify(mainView).showRepositories(any())
    }

    /**
     * When repo item is clicked , presenter should store
     * related data in the persistenceRepository and make the view to show
     * details page
     */
    @Test
    fun testRepoItemClicked() {

        //initialize presenter and bind a view to it
        val presenter = spy(MainPresenterImpl(apiService, persistenceRepository))
        val view: MainContract.MainView = mock()
        presenter.bind(view)

        //fake clicked item
        val username = "usernameValue"
        val repoName = "repoName"
        val owner = Owner(username,"")
        val fakeItem = GithubRepoResponseModel(repoName,owner,"",4)

        //update search query and perform the click
        presenter.onSearchQueryUpdate(username)
        presenter.onItemClicked(fakeItem)

        //verify data is stored and new view is about to be presented
        verify(persistenceRepository).persist(username,PresenterStateRepository.USERNAME_KEY)
        verify(persistenceRepository).persist(repoName,PresenterStateRepository.REPONAME_KEY)
        verify(view).showDetailsView()
    }

    /**
     * When search query is updated presenter should store
     * an updated value and be ready to fetch a new data when user executes
     * the Search
     */
    @Test
    fun testSearch() {
        //initialize presenter and put data in it
        val presenter = MainPresenterImpl(apiService, persistenceRepository)

        val updatedQuery = "newQuery"
        presenter.onSearchQueryUpdate(updatedQuery)

        //we verify that view show repositories was called
        val storedQuery = getPrivateMember(presenter, "mSearchQuery")
        assertEquals(storedQuery, updatedQuery)
    }

    /**
     * When data could not be fetched , error should be presented on the view
     */
    @Test
    fun testShowError() {
        //mock the service to throw error while retrieving data
        Mockito.`when`(apiService.getRepositories(any()))
                .thenReturn(Observable.error(Throwable("Intentional Exception")))

        //initialize presenter and put data in it
        val presenter = spy(MainPresenterImpl(apiService, persistenceRepository))
        val view: MainContract.MainView = mock()
        presenter.bind(view)

        presenter.onSearchClicked()
        verify(view).showError(any())
    }


}