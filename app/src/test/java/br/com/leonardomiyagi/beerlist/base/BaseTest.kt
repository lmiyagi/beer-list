package br.com.leonardomiyagi.beerlist.base

import org.mockito.Mockito

/**
 * Created by lmiyagi on 2/2/18.
 */
open class BaseTest {

    protected fun <T> any(): T = Mockito.any<T>()
}