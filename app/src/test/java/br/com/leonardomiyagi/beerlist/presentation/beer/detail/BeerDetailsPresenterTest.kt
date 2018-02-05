package br.com.leonardomiyagi.beerlist.presentation.beer.detail

import br.com.leonardomiyagi.beerlist.base.BaseTest
import br.com.leonardomiyagi.beerlist.data.local.utils.RealmNotFoundException
import br.com.leonardomiyagi.beerlist.domain.beer.DeleteBeer
import br.com.leonardomiyagi.beerlist.domain.beer.GetBeer
import br.com.leonardomiyagi.beerlist.domain.beer.StoreBeer
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.provider.SchedulerProvider
import br.com.leonardomiyagi.beerlist.domain.utils.ImageManager
import io.reactivex.Completable
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
 * Created by lmiyagi on 02/02/18.
 */
@RunWith(MockitoJUnitRunner::class)
class BeerDetailsPresenterTest : BaseTest() {

    @Mock
    lateinit var view: BeerDetailsContract.View
    @Mock
    lateinit var mockBeer: Beer
    @Mock
    lateinit var getBeer: GetBeer
    @Mock
    lateinit var storeBeer: StoreBeer
    @Mock
    lateinit var deleteBeer: DeleteBeer
    @Mock
    lateinit var imageManager: ImageManager
    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    lateinit var presenter: BeerDetailsContract.Presenter


    @Before
    fun setUp() {
        Mockito.`when`(schedulerProvider.io()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(schedulerProvider.main()).thenReturn(Schedulers.trampoline())

        presenter = BeerDetailsPresenter(mockBeer, getBeer, storeBeer, deleteBeer, imageManager, schedulerProvider)
    }

    @Test
    fun getStoredBeer_success() {
        val mockStoredBeer = mock(Beer::class.java)
        `when`(getBeer.execute(anyLong())).thenReturn(Single.just(mockStoredBeer))

        presenter.attachView(view)

        verify(view).renderBeer(mockStoredBeer)
    }

    @Test
    fun getStoredBeer_notFound() {
        `when`(getBeer.execute(anyLong())).thenReturn(Single.error(RealmNotFoundException()))

        presenter.attachView(view)

        verify(view).renderBeer(mockBeer)
    }

    @Test
    fun getStoredBeer_error() {
        `when`(getBeer.execute(anyLong())).thenReturn(Single.error(mock(Throwable::class.java)))

        presenter.attachView(view)

        verify(view).showGetBeerError()
    }

    @Test
    fun onFavoriteBeerClickedTest_success() {
        `when`(storeBeer.execute(mockBeer)).thenReturn(Completable.complete())
        `when`(view.handleBeerImage(any())).thenAnswer({ presenter.onImageProcessed(any()) })

        setupViewField()

        presenter.onFavoriteBeerClicked()

        verify(view).handleBeerImage(any())
        verify(view).showStoreBeerSuccess()
    }

    @Test
    fun onFavoriteBeerClickedTest_error() {
        `when`(storeBeer.execute(mockBeer)).thenReturn(Completable.error(mock(Throwable::class.java)))
        `when`(view.handleBeerImage(any())).thenAnswer({ presenter.onImageProcessed(any()) })

        setupViewField()

        presenter.onFavoriteBeerClicked()

        verify(view).showStoreBeerError()
    }

    @Test
    fun onUnfavoriteBeerClickedTest_success() {
        `when`(deleteBeer.execute(mockBeer)).thenReturn(Completable.complete())

        setupViewField()

        presenter.onUnfavoriteBeerClicked()

        verify(view).showDeleteBeerSuccess()
    }

    @Test
    fun onUnfavoriteBeerClickedTest_error() {
        `when`(deleteBeer.execute(mockBeer)).thenReturn(Completable.error(mock(Throwable::class.java)))

        setupViewField()

        presenter.onUnfavoriteBeerClicked()

        verify(view).showDeleteBeerError()
    }

    private fun setupViewField() {
        val fields: Array<Field> = presenter.javaClass.declaredFields
        val viewField: Field? = getField(fields, "view")
        viewField?.isAccessible = true
        viewField?.set(presenter, view)
    }
}