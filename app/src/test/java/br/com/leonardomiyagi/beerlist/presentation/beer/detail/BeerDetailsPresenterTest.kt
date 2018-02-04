package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import br.com.leonardomiyagi.beerlist.base.BaseTest
import br.com.leonardomiyagi.beerlist.domain.beer.StoreBeer
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.provider.SchedulerProvider
import io.reactivex.Completable
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
 * Created by lmiyagi on 02/02/18.
 */
@RunWith(MockitoJUnitRunner::class)
class BeerDetailsPresenterTest : BaseTest() {

    @Mock
    lateinit var view: BeerDetailsContract.View
    @Mock
    lateinit var mockBeer: Beer
    @Mock
    lateinit var storeBeer: StoreBeer
    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    lateinit var presenter: BeerDetailsContract.Presenter


    @Before
    fun setUp() {
        Mockito.`when`(schedulerProvider.io()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(schedulerProvider.main()).thenReturn(Schedulers.trampoline())

        presenter = BeerDetailsPresenter(mockBeer, storeBeer, schedulerProvider)
    }

    @Test
    fun renderBeer() {
        presenter.attachView(view)

        verify(view).renderBeer(mockBeer)
    }

    @Test
    fun onFavoriteBeerClickedTest_success() {
        `when`(storeBeer.execute(mockBeer)).thenReturn(Completable.complete())

        val fields: Array<Field> = presenter.javaClass.declaredFields
        val viewField: Field? = getField(fields, "view")
        viewField?.isAccessible = true
        viewField?.set(presenter, view)

        presenter.onFavoriteBeerClicked()

        verify(view).showStoreBeerSuccess()
    }

    @Test
    fun onFavoriteBeerClickedTest_error() {
        `when`(storeBeer.execute(mockBeer)).thenReturn(Completable.error(mock(Throwable::class.java)))

        val fields: Array<Field> = presenter.javaClass.declaredFields
        val viewField: Field? = getField(fields, "view")
        viewField?.isAccessible = true
        viewField?.set(presenter, view)

        presenter.onFavoriteBeerClicked()

        verify(view).showStoreBeerError()
    }
}