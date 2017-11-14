package com.asanarebel.yanbraslavski.asanarebeltask.persistence.impl

import com.asanarebel.yanbraslavski.asanarebeltask.persistence.PresenterStateRepository
import java.io.Serializable

/**
 * Created by yan.braslavski on 11/14/17.
 *
 * This implementation stores the data in local memory that will be persisted
 * as long as application is running. There are other possible solutions like serializing the
 * data in local storage or data base.
 */
class PresenterStateRepositoryImpl : PresenterStateRepository {

    private var dataMap: HashMap<String, Serializable> = HashMap()

    override fun persist(serializable: Serializable, key: String) {
        dataMap.put(key, serializable)
    }

    /**
     * This generic method will give us the data with the type we have requested , or null ...
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : Serializable> retrieve(key: String): T? {
        val data = dataMap[key]
        dataMap.remove(key)
        return data as? T
    }
}