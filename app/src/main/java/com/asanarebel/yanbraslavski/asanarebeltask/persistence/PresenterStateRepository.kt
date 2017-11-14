package com.asanarebel.yanbraslavski.asanarebeltask.persistence

import java.io.Serializable

/**
 * Created by yan.braslavski on 11/14/17.
 */
interface PresenterStateRepository {

    companion object {
        val USERNAME_KEY:String = "username_key"
        val REPONAME_KEY:String = "reponame_key"
    }

    fun persist(serializable: Serializable, key: String)
    fun <T : Serializable> retrieve(key: String): T?
}