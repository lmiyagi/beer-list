package br.com.leonardomiyagi.beerlist.presentation.main

import br.com.leonardomiyagi.beerlist.base.BaseTest
import br.com.leonardomiyagi.beerlist.data.utils.RequestException
import br.com.leonardomiyagi.beerlist.domain.beer.GetBeers
import br.com.leonardomiyagi.beerlist.domain.beer.GetFavoriteBeers
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
import java.lang.reflect.Field

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
    lateinit var getFavoriteBeers: GetFavoriteBeers
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

        presenter = MainPresenter(getBeers, getFavoriteBeers, schedulers, errorHandler)
    }

    @Test
    fun getUsersTest_success() {
        `when`(getBeers.execute()).thenReturn(Single.just(expectedBeers))

        presenter.attachView(view)

        verify(view).showLoading()
        verify(view).hidePlaceholders()
        verify(view).renderBeers(expectedBeers)
    }

    @Test
    fun getUsersTest_error() {
        `when`(getBeers.execute()).thenReturn(Single.error(mock(RequestException::class.java)))

        presenter.attachView(view)

        verify(view).showLoading()
        verify(errorHandler).handleError(any(), any())
        verify(view).showFetchError(any())
    }

    @Test
    fun onBeerClickedTest() {
        val beer = mock(Beer::class.java)

        val fields: Array<Field> = presenter.javaClass.declaredFields
        val viewField: Field? = getField(fields, "view")
        viewField?.isAccessible = true
        viewField?.set(presenter, view)

        presenter.onBeerClicked(beer)

        verify(view).goToBeerDetails(beer)
    }

    private fun setupExpectedBeers() {
        val beer = Mockito.mock(Beer::class.java)

        expectedBeers = ArrayList()
        expectedBeers.add(beer)
    }
}