package br.com.leonardomiyagi.beerlist.presentation.main

import br.com.leonardomiyagi.beerlist.base.BaseTest
import br.com.leonardomiyagi.beerlist.data.utils.RequestException
import br.com.leonardomiyagi.beerlist.domain.beer.GetBeers
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.beerlist.presentation.utils.ErrorHandler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by lmiyagi on 01/02/18.
 */
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest : BaseTest() {

    @Mock
    lateinit var getBeers: GetBeers
    @Mock
    lateinit var schedulers: SchedulerProvider
    @Mock
    lateinit var errorHandler: ErrorHandler
    @Mock
    lateinit var view: MainContract.View

    lateinit var expectedBeers: MutableList<Beer>
    lateinit var presenter: MainContract.Presenter


    @Before
    fun setUp() {
        `when`(schedulers.io()).thenReturn(Schedulers.trampoline())
        `when`(schedulers.main()).thenReturn(Schedulers.trampoline())
        setupExpectedBeers()

        presenter = MainPresenter(getBeers, schedulers, errorHandler)
    }

    @Test
    fun getUsers_success() {
        `when`(getBeers.execute()).thenReturn(Single.just(expectedBeers))

        presenter.attachView(view)

        verify(view).showLoading()
        verify(view).hidePlaceholders()
        verify(view).renderBeers(expectedBeers)
    }

    @Test
    fun getUsers_error() {
        `when`(getBeers.execute()).thenReturn(Single.error(mock(RequestException::class.java)))

        presenter.attachView(view)

        verify(view).showLoading()
        verify(errorHandler).handleError(any(), any())
        verify(view).showFetchError(any())
    }

    private fun setupExpectedBeers() {
        val beer = Mockito.mock(Beer::class.java)

        expectedBeers = ArrayList()
        expectedBeers.add(beer)
    }
}