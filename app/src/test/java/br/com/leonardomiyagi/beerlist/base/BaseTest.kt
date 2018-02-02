package br.com.leonardomiyagi.beerlist.base

import org.mockito.Mockito
import java.lang.reflect.Field

/**
 * Created by lmiyagi on 2/2/18.
 */
open class BaseTest {

    protected fun <T> any(): T = Mockito.any<T>()

    private fun getField(fields: Array<Field>, fieldName: String): Field? {
        fields.forEach { if (it.name == fieldName) return it }
        return null
    }
}