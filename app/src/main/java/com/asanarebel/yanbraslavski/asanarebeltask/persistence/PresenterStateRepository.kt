package com.asanarebel.yanbraslavski.asanarebeltask.persistence

import java.io.Serializable

/**
 * Created by yan.braslavski on 11/14/17.
 */
interface PresenterStateRepository {
    fun persist(serializable: Serializable, key: String)
    fun <T : Serializable> retrieve(key: String): T?
}