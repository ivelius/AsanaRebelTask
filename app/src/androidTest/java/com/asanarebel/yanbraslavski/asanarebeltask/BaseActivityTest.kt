package com.asanarebel.yanbraslavski.asanarebeltask

import android.support.test.espresso.intent.Intents
import com.affinitas.task.di.app.DaggerAppComponent
import com.affinitas.task.di.app.TestAppModule
import com.affinitas.task.utils.RxUtils

/**
 * Created by yan.braslavski on 11/15/17.
 */
abstract class BaseActivityTest {

    protected val mTestAppNodule = TestAppModule()

    open fun setup() {

        //Intents framework needed to test navigation between activities
        Intents.init()
        //Replace the app component modules with our test modules
        App.appComponent = DaggerAppComponent
                .builder()
                .appModule(mTestAppNodule)
                .build()

        //since we use RxKotlin , we need to adjust schedulers to espresso , so it could wait
        //while background operations are not complete
        RxUtils.prepareSchedulersForEspressoTesting()
    }

    open fun tearDown() {
        RxUtils.resetRxSchedulers()
        Intents.release()
    }
}