package com.asanarebel.yanbraslavski.asanarebeltask.main

import com.affinitas.task.api.GitHubService
import com.affinitas.task.utils.RxUtils
import com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses.GithubRepoResponseModel
import com.asanarebel.yanbraslavski.asanarebeltask.persistence.PresenterStateRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
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
import kotlin.reflect.KMutableProperty
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.memberProperties


/**
 * Test Suit for MainPresenter
 */
class MainPresenterUnitTest {

    companion object {
        private val USERNAME = "jakeworthon"
    }

    private val apiService: GitHubService = mock()
    private val persistenceRepository: PresenterStateRepository = mock()
    private lateinit var mPresenterUnderTest: MainContract.MainPresenter

    @Before
    fun setUp() {
        //We are not really interested in testing multithreaded loading at this point
        //So we just constrain all Rx operations for a single thread
        RxUtils.makeRxSchedulersImmidiate()

        //mock the service to return some empty data
        Mockito.`when`(apiService.getRepositories(USERNAME)).thenReturn(Observable.just(emptyList()))
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
     *
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

    private fun getPrivateMember(instance: Any, fieldName: String): Any? {
        val prop = instance::class.memberProperties.find { it.name == fieldName } as KMutableProperty<*>
        prop.getter.isAccessible = true
        return prop.getter.call(instance)
    }

    private fun setPrivateField(instance: Any, setData: Any, fieldName: String) {
        val p1Data = instance::class.memberProperties.find { it.name == fieldName } as KMutableProperty<*>
        p1Data.setter.isAccessible = true
        p1Data.setter.call(instance, setData)
    }

    /**
     * Whenever presenter has stored data ,
     * it should be immediately shown to the view
     */
    @Test
    fun testShowCachedDataOnBind() {

    }

    /**
     * Whenever presenter has no stored data ,
     * it should load the data from server and provide it to the view
     */
    @Test
    fun testDataLoadOnBind() {

    }

    /**
     * When repo item is clicked , presenter should store
     * related data in the persistenceRepository and make the view to show
     * details page
     */
    @Test
    fun testRepoItemClicked() {

    }

    /**
     * When search query is updated presenter should store
     * an updated value and be ready to fetch a new data when user executes
     * the Search
     */
    @Test
    fun testSearch() {

    }

    /**
     * When data could not be fetched , error should be presented on the view
     */
    @Test
    fun testShowError() {

    }


}