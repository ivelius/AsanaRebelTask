package com.asanarebel.yanbraslavski.asanarebeltask

import kotlin.reflect.KMutableProperty
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.memberProperties

/**
 * Created by yan.braslavski on 11/15/17.
 */
open class BaseUnitTest {
    protected fun getPrivateMember(instance: Any, fieldName: String): Any? {
        val prop = instance::class.memberProperties.find { it.name == fieldName } as KMutableProperty<*>
        prop.getter.isAccessible = true
        return prop.getter.call(instance)
    }

    protected fun setPrivateField(instance: Any, setData: Any, fieldName: String) {
        val p1Data = instance::class.memberProperties.find { it.name == fieldName } as KMutableProperty<*>
        p1Data.setter.isAccessible = true
        p1Data.setter.call(instance, setData)
    }
}