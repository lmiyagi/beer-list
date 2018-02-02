package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import br.com.leonardomiyagi.beerlist.base.BaseTest
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by lmiyagi on 02/02/18.
 */
@RunWith(MockitoJUnitRunner::class)
class BeerDetailsPresenterTest : BaseTest() {

    @Mock
    lateinit var view: BeerDetailsContract.View
    @Mock
    lateinit var mockBeer: Beer

    lateinit var presenter: BeerDetailsContract.Presenter


    @Before
    fun setUp() {
        presenter = BeerDetailsPresenter(mockBeer)
    }

    @Test
    fun renderBeer() {
        presenter.attachView(view)

        verify(view).renderBeer(mockBeer)
    }
}